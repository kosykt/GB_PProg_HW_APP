package com.example.gb_pprog.domain

import com.example.gb_pprog.domain.model.FavoriteModel

class GetAllFavoritesUseCase(
    private val domainRepository: DomainRepository,
) {
    suspend fun execute(): List<FavoriteModel> {
        return domainRepository.getAllFavorite()
    }
}