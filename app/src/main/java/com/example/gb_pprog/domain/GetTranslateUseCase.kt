package com.example.gb_pprog.domain

import com.example.gb_pprog.domain.model.DomainModel

class GetTranslateUseCase(
    private val domainRepository: DomainRepository,
) {
    suspend fun execute(word: String): List<DomainModel> = domainRepository.translate(word)
}