package com.example.androidbootcampbitirmeprojesi.data.repo

import com.example.androidbootcampbitirmeprojesi.data.datasource.YemeklerDataSource
import com.example.androidbootcampbitirmeprojesi.data.entity.Yemekler
import com.example.androidbootcampbitirmeprojesi.data.entity.YemeklerSepet

class YemeklerRepository {

    private val yemeklerDataSource = YemeklerDataSource()

    suspend fun yemekleriYukle():List<Yemekler> = yemeklerDataSource.yemekleriYukle()
    suspend fun sepettekiYemekleriYukle(kullaniciAdi: String): List<YemeklerSepet> {
        return yemeklerDataSource.sepettekiYemekleriGetir(kullaniciAdi) ?: emptyList()
    }
    suspend fun sepeteYemekEkle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ): Boolean = yemeklerDataSource.sepeteYemekEkle(
        yemek_adi,
        yemek_resim_adi,
        yemek_fiyat,
        yemek_siparis_adet,
        kullanici_adi
    )
    suspend fun sepettekiYemekSil(sepet_yemek_id: String, kullanici_adi: String): Boolean {
        return yemeklerDataSource.sepettekiYemekSil(sepet_yemek_id, kullanici_adi)
    }


}