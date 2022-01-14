package com.example.gb_pprog.domain

import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import io.reactivex.rxjava3.core.Single

class SearchWordUseCase(private val domainRepository: DomainRepository) {

    fun execute(word: String): Single<List<RetrofitTranslateDto>> = domainRepository.translate(word)
}