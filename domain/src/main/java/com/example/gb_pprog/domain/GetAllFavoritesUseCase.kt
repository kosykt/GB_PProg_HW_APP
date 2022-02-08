package com.example.gb_pprog.domain

import com.example.gb_pprog.domain.model.FavoriteModel
import kotlinx.coroutines.flow.Flow

class GetAllFavoritesUseCase(
    private val domainRepository: DomainRepository,
) {
    fun execute(): Flow<List<FavoriteModel>> {
        return domainRepository.getAllFavorite()
    }
}