package com.example.gb_pprog.domain.model

import java.io.Serializable

data class DomainModel(
    val id: Int,
    val meanings: List<DomainMeaning>,
    val text: String,
) : Serializable
