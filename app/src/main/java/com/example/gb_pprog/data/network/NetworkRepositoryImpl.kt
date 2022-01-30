package com.example.gb_pprog.data.network

import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import com.example.gb_pprog.data.repository.NetworkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetworkRepositoryImpl(
    private val retrofitService: RetrofitService,
) : NetworkRepository {

    override fun getData(word: String): Flow<List<RetrofitTranslateDto>> {
        return flow {
            emit(retrofitService.getNetworkData(word))
        }
    }
}