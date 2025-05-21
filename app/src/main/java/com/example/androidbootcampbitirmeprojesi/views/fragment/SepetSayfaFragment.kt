package com.example.androidbootcampbitirmeprojesi.views.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.androidbootcampbitirmeprojesi.databinding.FragmentSepetSayfaBinding
import com.example.androidbootcampbitirmeprojesi.views.viewModel.SepetSayfaViewModel
import com.example.androidbootcampbitirmeprojesi.views.viewModel.YemekDetayViewModel

class SepetSayfaFragment : Fragment() {
    private lateinit var binding: FragmentSepetSayfaBinding
    private lateinit var viewModel: SepetSayfaViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSepetSayfaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SepetSayfaViewModel by viewModels()
        viewModel=tempViewModel
    }

}