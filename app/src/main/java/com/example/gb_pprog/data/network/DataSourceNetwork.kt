package com.example.gb_pprog.data.network

import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import com.example.gb_pprog.data.repository.DataSourceRepository
import io.reactivex.rxjava3.core.Single

class DataSourceNetwork(private val retrofitService: RetrofitService): DataSourceRepository {

    override fun getData(word: String): Single<RetrofitTranslateDto> {
        return retrofitService.getNetworkData(word)
    }
}