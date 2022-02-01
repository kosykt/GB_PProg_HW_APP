package com.example.gb_pprog.domain

import com.example.gb_pprog.domain.model.DomainModel
import kotlinx.coroutines.flow.Flow

class GetTranslateUseCase(
    private val domainRepository: DomainRepository,
) {
    fun execute(word: String): Flow<List<DomainModel>> = domainRepository.translate(word)
}