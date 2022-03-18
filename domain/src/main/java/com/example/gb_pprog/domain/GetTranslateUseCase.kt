package com.example.gb_pprog.domain

import com.example.gb_pprog.domain.model.DomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetTranslateUseCase(
    private val domainRepository: DomainRepository,
) {
    fun execute(word: String): Flow<List<DomainModel>> {
        return if (word.isEmpty()) {
            flow {
                emit(emptyList())
            }
        } else {
            flow {
                emit(domainRepository.translate(word))
            }
        }
    }
}