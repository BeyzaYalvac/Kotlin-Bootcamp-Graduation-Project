package com.example.androidbootcampbitirmeprojesi.views.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidbootcampbitirmeprojesi.data.entity.Yemekler
import com.example.androidbootcampbitirmeprojesi.data.entity.YemeklerSepet
import com.example.androidbootcampbitirmeprojesi.data.repo.YemeklerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SepetSayfaViewModel:ViewModel() {
    val sepettekiYemeklerListesi = MutableLiveData<List<YemeklerSepet>>()

    var yemeklerRepository= YemeklerRepository()

    init {
        sepettekiYemekleriYukle()
    }

    fun sepettekiYemekleriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            val liste = yemeklerRepository.sepettekiYemekleriYukle("Beyzay")

            if (liste != null) {
                Log.d("ViewModelSepet", "Liste boyutu: ${liste.size}")
                sepettekiYemeklerListesi.value = liste
            } else {
                Log.e("ViewModelSepet", "Liste null geldi")
                sepettekiYemeklerListesi.value = emptyList()
            }

        }

    }

    fun sepettekiYemekSil(sepet_yemek_id: String, kullanici_adi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val sonuc = yemeklerRepository.sepettekiYemekSil(sepet_yemek_id, kullanici_adi)
            if (sonuc) {
                sepettekiYemekleriYukle() // 🔁 Listeyi güncelle
            }
        }
    }

}