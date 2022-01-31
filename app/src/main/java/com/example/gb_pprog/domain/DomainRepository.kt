package com.example.gb_pprog.domain

import com.example.gb_pprog.domain.model.DomainModel

interface DomainRepository {

    suspend fun translate(word: String): List<DomainModel>
}