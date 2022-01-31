package com.example.gb_pprog.data.network

import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import com.example.gb_pprog.data.repository.DataSourceRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DataSourceNetwork @Inject constructor(
    private val retrofitService: RetrofitService
) : DataSourceRepository {

    override fun getData(word: String): Single<List<RetrofitTranslateDto>> {
        return retrofitService.getNetworkData(word)
    }
}