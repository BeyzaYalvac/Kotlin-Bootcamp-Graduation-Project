package com.example.androidbootcampbitirmeprojesi.views.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidbootcampbitirmeprojesi.data.entity.Yemekler
import com.example.androidbootcampbitirmeprojesi.data.entity.YemeklerSepet
import com.example.androidbootcampbitirmeprojesi.databinding.CardSepetTasarimBinding
import com.example.androidbootcampbitirmeprojesi.databinding.UrunCardTasarimBinding
import com.example.androidbootcampbitirmeprojesi.views.viewModel.SepetSayfaViewModel

class YemekSepetAdapter(
    val mContext: Context,
    val sepettekiYemeklerListesi: List<YemeklerSepet>, // ✅ Mutable değil!
    val viewModel: SepetSayfaViewModel,
    private val listener: SepetListener
) : RecyclerView.Adapter<YemekSepetAdapter.CardTasarimSepetHolder>() {
    inner class CardTasarimSepetHolder(var binding: CardSepetTasarimBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimSepetHolder {
        val tasarim = CardSepetTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimSepetHolder(tasarim)    }

    override fun getItemCount(): Int {
        return sepettekiYemeklerListesi.size
    }

    interface SepetListener {
        fun onSepetGuncellendi()
    }

    override fun onBindViewHolder(holder: CardTasarimSepetHolder, position: Int) {
        val yemek = sepettekiYemeklerListesi.get(position)//0,1,2
        val t = holder.binding
        val mutableYemekListesi = sepettekiYemeklerListesi.toMutableList()

        t.textViewYemekAdiSepet.text=yemek.yemek_adi
t.textViewYemekTaneSepet.text=yemek.yemek_siparis_adet.toString()
        t.textViewYemekFiyatiSepet.text=yemek.yemek_fiyat.toString()
        t.imageViewYemekSilSepet.setOnClickListener {
            val silinen = mutableYemekListesi.removeAt(position)
            viewModel.sepettekiYemekSil(silinen.sepet_yemek_id, silinen.kullanici_adi)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, sepettekiYemeklerListesi.size)
        }

        t.imageViewRemoveYemekSepet.setOnClickListener{
            val adet = t.textViewYemekTaneSepet.text.toString().toInt()
            if (adet > 1) {
                t.textViewYemekTaneSepet.text = (adet - 1).toString()
                listener.onSepetGuncellendi()
            }else{
                val silinen = mutableYemekListesi.removeAt(position)
                viewModel.sepettekiYemekSil(silinen.sepet_yemek_id, silinen.kullanici_adi)
                notifyItemRemoved(position)
            }

        }

        val imageUrl = "http://kasimadalan.pe.hu/yemekler/resimler/${yemek.yemek_resim_adi}"
        Log.d("Adapter", sepettekiYemeklerListesi.toString())
        Glide.with(mContext)
            .load(imageUrl)
            .override(350, 350)
            .into(t.imageViewYemekSepet)
    }
}