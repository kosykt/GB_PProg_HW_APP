package com.example.gb_pprog.data.network.model

data class RetrofitTranslateDto(
    val id: Int,
    val meanings: List<Meaning>,
    val text: String
)