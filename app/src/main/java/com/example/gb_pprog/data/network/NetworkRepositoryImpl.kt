package com.example.gb_pprog.data.network

import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import com.example.gb_pprog.data.repository.NetworkRepository

class NetworkRepositoryImpl(
    private val retrofitService: RetrofitService,
) : NetworkRepository {

    override suspend fun getData(word: String): List<RetrofitTranslateDto> {
        return retrofitService.getNetworkData(word)
    }
}