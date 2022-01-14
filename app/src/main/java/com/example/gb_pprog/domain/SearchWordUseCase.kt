package com.example.gb_pprog.domain

import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import io.reactivex.rxjava3.core.Observable

class SearchWordUseCase(private val domainRepository: DomainRepository) {

    fun execute(word: String): Observable<List<RetrofitTranslateDto>> = domainRepository.translate(word)
}