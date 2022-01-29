package com.example.gb_pprog.domain

import com.example.gb_pprog.domain.model.DomainModel
import com.example.gb_pprog.domain.model.FavoriteModel

interface DomainRepository {

    suspend fun translate(word: String): List<DomainModel>

    suspend fun saveFavorite(domainModel: DomainModel)

    suspend fun getAllFavorite(): List<FavoriteModel>
}