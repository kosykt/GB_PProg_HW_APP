package com.example.gb_pprog.data.repository

import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import com.example.gb_pprog.domain.DomainRepository
import com.example.gb_pprog.domain.model.DomainModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class DomainRepositoryImpl(private val dataSource: DataSourceRepository) : DomainRepository {

    override fun translate(word: String): Single<RetrofitTranslateDto> {
        return dataSource.getData(word)
    }
}
