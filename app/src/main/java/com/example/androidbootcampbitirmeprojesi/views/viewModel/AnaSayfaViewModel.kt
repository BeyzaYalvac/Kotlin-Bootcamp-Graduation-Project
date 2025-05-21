package com.example.androidbootcampbitirmeprojesi.views.viewModel

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
fun kisileriYukle(){
    CoroutineScope(Dispatchers.Main).launch {
        yemeklerListesi.value=yemeklerRepository.yemekleriYukle()

    }
}
}