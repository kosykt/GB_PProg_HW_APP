package com.example.gb_pprog.data.repository

import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import io.reactivex.rxjava3.core.Observable

interface DataSourceRepository {

    fun getData(word: String): Observable<RetrofitTranslateDto>
}