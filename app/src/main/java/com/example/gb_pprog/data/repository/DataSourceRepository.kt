package com.example.gb_pprog.data.repository

import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import io.reactivex.rxjava3.core.Single

interface DataSourceRepository {

    fun getData(word: String): Single<List<RetrofitTranslateDto>>
}