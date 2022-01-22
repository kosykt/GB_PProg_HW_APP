package com.example.gb_pprog.data.network

import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import com.example.gb_pprog.data.repository.DataSourceRepository

class DataSourceNetwork(
    private val retrofitService: RetrofitService,
) : DataSourceRepository {

    override suspend fun getData(word: String): List<RetrofitTranslateDto> {
        return retrofitService.getNetworkData(word)
    }
}