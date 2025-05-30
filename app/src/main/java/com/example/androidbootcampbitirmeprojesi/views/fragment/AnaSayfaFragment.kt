package com.example.androidbootcampbitirmeprojesi.views.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidbootcampbitirmeprojesi.data.entity.Yemekler
import com.example.androidbootcampbitirmeprojesi.databinding.FragmentAnaSayfaBinding
import com.example.androidbootcampbitirmeprojesi.views.adapter.YemekAdapter
import com.example.androidbootcampbitirmeprojesi.views.viewModel.AnaSayfaViewModel

class AnaSayfaFragment : Fragment() {
private lateinit var binding: FragmentAnaSayfaBinding
private lateinit var viewModel:AnaSayfaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAnaSayfaBinding.inflate(inflater, container, false)
        viewModel.filteredYemekler.observe(viewLifecycleOwner){
    var yemeklerAdapter=YemekAdapter(requireContext(),it,viewModel)
    binding.YemeklerRv.adapter= yemeklerAdapter
}

        binding.YemeklerRv.layoutManager= GridLayoutManager(requireContext(),2)

        binding.searchViewYemekAra.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filterYemekler(newText.orEmpty())
                return true
            }
        })
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:AnaSayfaViewModel by viewModels()
        viewModel=tempViewModel
    }
}