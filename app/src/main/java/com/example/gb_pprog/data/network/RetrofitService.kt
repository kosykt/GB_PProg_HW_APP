package com.example.gb_pprog.data.network

import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("words/search")
    suspend fun getNetworkData(@Query("search") word: String): List<RetrofitTranslateDto>
}
