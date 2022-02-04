package com.example.gb_pprog.domain

import com.example.gb_pprog.domain.model.FavoriteModel

class DeleteFavoriteUseCase(
    private val domainRepository: DomainRepository,
) {
    suspend fun execute(model: FavoriteModel) {
        domainRepository.deleteFavorite(model)
    }
}