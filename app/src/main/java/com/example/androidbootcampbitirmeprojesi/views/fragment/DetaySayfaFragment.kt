package com.example.androidbootcampbitirmeprojesi.views.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.androidbootcampbitirmeprojesi.databinding.FragmentDetaySayfaBinding
import com.example.androidbootcampbitirmeprojesi.views.viewModel.AnaSayfaViewModel
import com.example.androidbootcampbitirmeprojesi.views.viewModel.YemekDetayViewModel


class DetaySayfaFragment : Fragment() {
    private lateinit var binding: FragmentDetaySayfaBinding
    private lateinit var viewModel:YemekDetayViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentDetaySayfaBinding.inflate(inflater, container, false)
        val bundle:DetaySayfaFragmentArgs by navArgs()
        val gelenYemek=bundle.yemek
        binding.textViewYemekDetayAdi.text=gelenYemek.yemek_adi
        binding.textViewYemekFiyat.text=gelenYemek.yemek_fiyat.toString()
        val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"

        Glide.with(requireContext())
            .load(imageUrl)
            .override(650, 650)
            .into(binding.imageViewYemekResmi)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: YemekDetayViewModel by viewModels()
        viewModel=tempViewModel
    }

}