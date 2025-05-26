package com.example.androidbootcampbitirmeprojesi.data.datasource

import android.util.Log
import com.example.androidbootcampbitirmeprojesi.data.entity.Yemekler
import com.example.androidbootcampbitirmeprojesi.data.entity.YemeklerSepet
import com.example.androidbootcampbitirmeprojesi.data.retrofit.ApiUtils
import com.example.androidbootcampbitirmeprojesi.data.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource {
    var yemeklerDao= ApiUtils.getYemeklerDao()

    suspend fun yemekleriYukle():List<Yemekler> = withContext(Dispatchers.IO){
        return@withContext yemeklerDao.yemekleriYukle().yemekler
    }

    suspend fun sepettekiYemekleriGetir(kullanici_adi: String): List<YemeklerSepet>? = withContext(Dispatchers.IO) {
        try {
            val cevap = yemeklerDao.sepettekiYemekleriGetir(kullanici_adi)
            Log.d("DataSource", "Success: ${cevap.success} | Message: ${cevap.sepet_yemekler}")
            return@withContext cevap.sepet_yemekler ?: emptyList()
        } catch (e: Exception) {
            Log.e("DataSource", "API hatası: ${e.localizedMessage}")
            return@withContext emptyList()
        }
    }

    suspend fun sepettekiYemekSil(sepet_yemek_id: String, kullanici_adi: String): Boolean = withContext(Dispatchers.IO) {
        val cevap = yemeklerDao.sepettekiYemekSil(sepet_yemek_id, kullanici_adi)
        Log.d("Silme", "Success: ${cevap.success}, Message: ${cevap.message}")
        return@withContext cevap.success == 1
    }

    suspend fun sepeteYemekEkle(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ): Boolean = withContext(Dispatchers.IO) {
        val cevap = yemeklerDao.sepeteYemekEkle(
            yemek_adi,
            yemek_resim_adi,
            yemek_fiyat,
            yemek_siparis_adet,
            kullanici_adi
        )
        Log.e("Sepete Ekle", "Success : ${cevap.success} - Message : ${cevap.message}")
        return@withContext cevap.success == 1
    }

}