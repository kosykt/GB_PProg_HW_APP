package com.example.gb_pprog.domain

import com.example.gb_pprog.domain.model.DomainModel
import com.example.gb_pprog.domain.model.FavoriteModel
import kotlinx.coroutines.flow.Flow

interface DomainRepository {

    fun translate(word: String): Flow<List<DomainModel>>

    suspend fun saveFavorite(domainModel: DomainModel)

    fun getAllFavorite(): Flow<List<FavoriteModel>>

    suspend fun deleteFavorite(model: FavoriteModel)
}