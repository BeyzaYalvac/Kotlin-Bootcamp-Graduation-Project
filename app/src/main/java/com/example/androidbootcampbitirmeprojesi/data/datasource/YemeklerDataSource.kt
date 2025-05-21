package com.example.androidbootcampbitirmeprojesi.data.datasource

import com.example.androidbootcampbitirmeprojesi.data.entity.Yemekler
import com.example.androidbootcampbitirmeprojesi.data.retrofit.ApiUtils
import com.example.androidbootcampbitirmeprojesi.data.retrofit.YemeklerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class YemeklerDataSource {
    var yemeklerDao= ApiUtils.getYemeklerDao()

    suspend fun yemekleriYukle():List<Yemekler> = withContext(Dispatchers.IO){
        return@withContext yemeklerDao.yemekleriYukle().yemekler
    }
}