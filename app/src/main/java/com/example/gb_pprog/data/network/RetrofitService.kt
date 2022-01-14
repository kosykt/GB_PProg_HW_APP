package com.example.gb_pprog.data.network

import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("words/search")
    fun getNetworkData(@Query("search") word: String): Observable<List<RetrofitTranslateDto>>
}
