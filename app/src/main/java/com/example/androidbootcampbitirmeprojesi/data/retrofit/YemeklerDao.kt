package com.example.androidbootcampbitirmeprojesi.data.retrofit

import com.example.androidbootcampbitirmeprojesi.data.entity.CRUDCevap
import com.example.androidbootcampbitirmeprojesi.data.entity.Yemekler
import com.example.androidbootcampbitirmeprojesi.data.entity.YemeklerCevap
import com.example.androidbootcampbitirmeprojesi.data.entity.sepettekiYemeklerCevap
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface YemeklerDao {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun yemekleriYukle():YemeklerCevap

    @FormUrlEncoded
    @POST("yemekler/sepettekiYemekleriGetir.php")
    suspend fun sepettekiYemekleriGetir(
        @Field("kullanici_adi") kullanici_adi: String
    ): sepettekiYemeklerCevap


    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun sepeteYemekEkle(@Field("yemek_adi") yemek_adi:String,
                                @Field("yemek_resim_adi") yemek_resim_adi:String,
                                @Field("yemek_fiyat") yemek_fiyat:Int,
                                @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                                @Field("kullanici_adi") kullanici_adi:String):CRUDCevap

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun sepettekiYemekSil(
        @Field("sepet_yemek_id") sepet_yemek_id: String,
        @Field("kullanici_adi") kullanici_adi: String
    ): CRUDCevap


}