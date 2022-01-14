package com.example.gb_pprog.domain

import com.example.gb_pprog.domain.model.DomainModel
import io.reactivex.rxjava3.core.Observable

class SearchWordUseCase(private val domainRepository: DomainRepository) {

    fun execute(word: String): Observable<DomainModel> = domainRepository.translate(word)
}