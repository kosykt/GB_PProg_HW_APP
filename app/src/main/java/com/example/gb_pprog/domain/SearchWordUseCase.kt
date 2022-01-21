package com.example.gb_pprog.domain

import com.example.gb_pprog.domain.model.DomainModel
import io.reactivex.rxjava3.core.Single

class SearchWordUseCase(
    private val domainRepository: DomainRepository
) {
    fun execute(word: String): Single<List<DomainModel>> = domainRepository.translate(word)
}