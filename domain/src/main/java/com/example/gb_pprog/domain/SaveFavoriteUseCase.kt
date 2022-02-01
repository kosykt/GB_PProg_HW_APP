package com.example.gb_pprog.domain

import com.example.gb_pprog.domain.model.DomainModel

class SaveFavoriteUseCase(
    private val domainRepository: DomainRepository,
) {
    suspend fun execute(domainModel: DomainModel) {
        domainRepository.saveFavorite(domainModel)
    }
}