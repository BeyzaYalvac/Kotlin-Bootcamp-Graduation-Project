package com.example.androidbootcampbitirmeprojesi.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidbootcampbitirmeprojesi.data.entity.Yemekler
import com.example.androidbootcampbitirmeprojesi.databinding.UrunCardTasarimBinding
import com.example.androidbootcampbitirmeprojesi.views.fragment.AnaSayfaFragmentDirections
import com.example.androidbootcampbitirmeprojesi.views.viewModel.AnaSayfaViewModel


class YemekAdapter(var mContext: Context, var yemeklerListesi:List<Yemekler>,viewModel: AnaSayfaViewModel):RecyclerView.Adapter<YemekAdapter.CardTasarimHolder>() {
    inner class CardTasarimHolder(var binding: UrunCardTasarimBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimHolder {
        val tasarim = UrunCardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimHolder(tasarim)
    }

    override fun getItemCount(): Int {
        return yemeklerListesi.size
    }

    override fun onBindViewHolder(holder: CardTasarimHolder, position: Int) {
        val yemek = yemeklerListesi.get(position)//0,1,2
        val t = holder.binding
        t.textViewYemekAdi.text = yemek.yemek_adi
        t.textViewFiyat.text=yemek.yemek_fiyat.toString()

        val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"

        Glide.with(mContext)
            .load(imageUrl)
            .override(350, 350)
            .into(t.imageViewYemek)

        t.CardViewUrun.setOnClickListener {
            val gecis=AnaSayfaFragmentDirections.yemekDetayGecis(yemek=yemek)
            Navigation.findNavController(it).navigate(gecis)

        }
    }

}