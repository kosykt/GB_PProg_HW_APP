package com.example.gb_pprog.data.repository

import com.example.gb_pprog.data.network.RetrofitService
import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import com.example.gb_pprog.domain.DomainRepository
import io.reactivex.rxjava3.core.Observable

class DomainRepositoryImpl(private val retrofitService: RetrofitService) : DomainRepository {

    override fun translate(word: String): Observable<RetrofitTranslateDto> {
        return retrofitService.search(word)
    }
}