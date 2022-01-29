package com.example.gb_pprog.data.repository

import com.example.gb_pprog.domain.DomainRepository
import com.example.gb_pprog.domain.model.DomainModel
import com.example.gb_pprog.domain.model.FavoriteModel

class DomainRepositoryImpl(
    private val network: NetworkRepository,
    private val database: DatabaseRepository,
) : DomainRepository {

    override suspend fun translate(word: String): List<DomainModel> {
        return network.getData(word).toListDomainModel()
    }

    override suspend fun saveFavorite(domainModel: DomainModel) {
        database.insert(domainModel.toRoomModel())
    }

    override suspend fun getAllFavorite(): List<FavoriteModel> {
        return database.getAll().map { it.toFavoriteModel() }
    }

    override suspend fun deleteFavorite(model: FavoriteModel) {
        database.delete(model.toRoomModel())
    }
}