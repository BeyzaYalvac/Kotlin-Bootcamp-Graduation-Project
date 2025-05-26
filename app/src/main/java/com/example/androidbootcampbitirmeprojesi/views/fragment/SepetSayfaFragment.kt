package com.example.androidbootcampbitirmeprojesi.views.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidbootcampbitirmeprojesi.data.entity.YemeklerSepet
import com.example.androidbootcampbitirmeprojesi.databinding.FragmentSepetSayfaBinding
import com.example.androidbootcampbitirmeprojesi.views.adapter.YemekAdapter
import com.example.androidbootcampbitirmeprojesi.views.adapter.YemekSepetAdapter
import com.example.androidbootcampbitirmeprojesi.views.viewModel.SepetSayfaViewModel
import com.example.androidbootcampbitirmeprojesi.views.viewModel.YemekDetayViewModel

class SepetSayfaFragment : Fragment(), YemekSepetAdapter.SepetListener {
    private lateinit var binding: FragmentSepetSayfaBinding
    private lateinit var viewModel: SepetSayfaViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSepetSayfaBinding.inflate(inflater, container, false)

        binding.YemekSepetRV.adapter = YemekSepetAdapter(requireContext(), emptyList(), viewModel,this )
        binding.YemekSepetRV.layoutManager = LinearLayoutManager(requireContext())

        viewModel.sepettekiYemeklerListesi.observe(viewLifecycleOwner) { yemekListesi ->
            Log.d("SepetGeldiMi", "Yemek listesi: ${yemekListesi?.size} adet")

            val adapter = YemekSepetAdapter(
                requireContext(),
                yemekListesi ?: emptyList(),
                viewModel,
                this
            )

            binding.YemekSepetRV.adapter = adapter

            if (!yemekListesi.isNullOrEmpty()) {
                binding.YemekSepetRV.visibility = View.VISIBLE
                binding.emptyTextView.visibility = View.GONE
            } else {
                binding.YemekSepetRV.visibility = View.GONE
                binding.emptyTextView.visibility = View.VISIBLE
            }

            var sepetTutar=0
            for(yemek in viewModel.sepettekiYemeklerListesi.value!!){
                sepetTutar += (yemek.yemek_fiyat.toInt()*yemek.yemek_siparis_adet.toInt())
            }
            binding.textViewSepetTutar.text= sepetTutar.toString()

            binding.buttonSepetiBosalt.setOnClickListener{
                for(yemek in viewModel.sepettekiYemeklerListesi.value!!){
                    viewModel.sepettekiYemekSil(yemek.sepet_yemek_id,yemek.kullanici_adi)
                }
                AlertDialog.Builder(requireContext())
                    .setTitle("Sepetiniz Boşaltıldı")
                    .setPositiveButton("Tamam") { dialog, _ ->
                        dialog.dismiss()
                    }.show()


            }

        }

        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SepetSayfaViewModel by viewModels()
        viewModel=tempViewModel

    }

    override fun onSepetGuncellendi() {
        val yemekListesi = viewModel.sepettekiYemeklerListesi.value ?: return
        var sepetTutar = 0
        for (yemek in yemekListesi) {
            sepetTutar += (yemek.yemek_fiyat.toInt() * yemek.yemek_siparis_adet.toInt())
        }
        binding.textViewSepetTutar.text = sepetTutar.toString()
    }

}