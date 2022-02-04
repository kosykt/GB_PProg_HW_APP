package com.example.gb_pprog.data.repository

import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {

    fun getData(word: String): Flow<List<RetrofitTranslateDto>>
}