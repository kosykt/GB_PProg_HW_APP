package com.example.gb_pprog.data.repository

import com.example.gb_pprog.data.network.model.RetrofitTranslateDto

interface DataSourceRepository {

    suspend fun getData(word: String): List<RetrofitTranslateDto>
}