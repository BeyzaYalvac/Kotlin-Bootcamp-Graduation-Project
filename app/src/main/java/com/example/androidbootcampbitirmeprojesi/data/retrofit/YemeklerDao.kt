package com.example.androidbootcampbitirmeprojesi.data.retrofit

import com.example.androidbootcampbitirmeprojesi.data.entity.Yemekler
import com.example.androidbootcampbitirmeprojesi.data.entity.YemeklerCevap
import retrofit2.http.GET

interface YemeklerDao {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun yemekleriYukle():YemeklerCevap

}