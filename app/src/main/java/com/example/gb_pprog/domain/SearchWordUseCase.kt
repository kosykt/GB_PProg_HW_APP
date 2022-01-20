package com.example.gb_pprog.domain

import com.example.gb_pprog.domain.model.DomainModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchWordUseCase @Inject constructor(
    private val domainRepository: DomainRepository
) {
    fun execute(word: String): Single<List<DomainModel>> = domainRepository.translate(word)
}