package com.example.gb_pprog.data.repository

import com.example.gb_pprog.data.database.model.RoomModel
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    fun insert(model: RoomModel)

    fun getAll(): Flow<List<RoomModel>>

    suspend fun delete(model: RoomModel)
}