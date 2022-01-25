package com.example.gb_pprog.domain.model


data class DomainModel(
    val id: Int,
    val meanings: List<DomainMeaning>,
    val text: String
)
