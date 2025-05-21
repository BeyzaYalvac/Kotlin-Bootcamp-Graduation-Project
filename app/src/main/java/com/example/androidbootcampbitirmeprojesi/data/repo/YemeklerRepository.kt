package com.example.androidbootcampbitirmeprojesi.data.repo

import com.example.androidbootcampbitirmeprojesi.data.datasource.YemeklerDataSource
import com.example.androidbootcampbitirmeprojesi.data.entity.Yemekler

class YemeklerRepository {

    private val yemeklerDataSource = YemeklerDataSource()

    suspend fun yemekleriYukle():List<Yemekler> = yemeklerDataSource.yemekleriYukle()
}