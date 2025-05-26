package com.example.androidbootcampbitirmeprojesi.data.entity

data class sepettekiYemeklerCevap(
    val sepet_yemekler: List<YemeklerSepet>?,
    val success: Int,
    val message: String
) {
}