package com.example.gb_pprog.data.repository

import com.example.gb_pprog.domain.DomainRepository
import com.example.gb_pprog.domain.model.DomainModel
import com.example.gb_pprog.domain.model.FavoriteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class DomainRepositoryImpl(
    private val network: NetworkRepository,
    private val database: DatabaseRepository,
) : DomainRepository {

    override fun translate(word: String): Flow<List<DomainModel>> {
        return flow {
            emit(network.getData(word).toListDomainModel())
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun saveFavorite(domainModel: DomainModel) {
        database.insert(domainModel.toRoomModel())
    }

    override fun getAllFavorite(): Flow<List<FavoriteModel>> {
        return database.getAll().map {
            it.toFavoriteModel()
        }
    }

    override suspend fun deleteFavorite(model: FavoriteModel) {
        database.delete(model.toRoomModel())
    }
}