package com.example.androidbootcampbitirmeprojesi.views.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidbootcampbitirmeprojesi.data.entity.Yemekler
import com.example.androidbootcampbitirmeprojesi.data.repo.YemeklerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnaSayfaViewModel:ViewModel() {
var yemeklerListesi=MutableLiveData<List<Yemekler>>()
    var yemeklerRepository= YemeklerRepository()
    init {
        kisileriYukle()
    }
fun kisileriYukle() {
    CoroutineScope(Dispatchers.Main).launch {
        yemeklerListesi.value = yemeklerRepository.yemekleriYukle()
        setYemekler(yemeklerListesi.value)
    }
    }

    fun sepeteYemekEkle(yemek: Yemekler, adet: Int, kullaniciAdi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val success = yemeklerRepository.sepeteYemekEkle(
                yemek.yemek_adi,
                yemek.yemek_resim_adi,
                yemek.yemek_fiyat,
                adet,
                kullaniciAdi
            )

            Log.d("SepetEkleDurum", "yemek adı: ${yemek.yemek_adi} adet: $adet kullanıcı adı: $kullaniciAdi")
            Log.d("SepetEkleDurum", "Ekleme başarılı mı? $success")
        }
    }

    private var allYemekler = listOf<Yemekler>()
    private val _filteredYemekler = MutableLiveData<List<Yemekler>>()
    val filteredYemekler: LiveData<List<Yemekler>> = _filteredYemekler

    fun setYemekler(yemekList: List<Yemekler>) {
        allYemekler = yemekList
        _filteredYemekler.value = yemekList
    }

    fun filterYemekler(query: String) {
        _filteredYemekler.value = if (query.isEmpty()) {
            allYemekler
        } else {
            allYemekler.filter { it.yemek_adi.contains(query, ignoreCase = true) }
        }
    }

}