package com.example.gb_pprog.data.repository

import com.example.gb_pprog.domain.DomainRepository
import com.example.gb_pprog.domain.model.DomainModel

class DomainRepositoryImpl(
    private val dataSource: DataSourceRepository
) : DomainRepository {

    override suspend fun translate(word: String): List<DomainModel> {
        return dataSource.getData(word).toListDomainModel()
    }
}