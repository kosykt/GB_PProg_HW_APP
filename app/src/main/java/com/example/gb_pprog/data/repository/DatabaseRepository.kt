package com.example.gb_pprog.data.repository

import com.example.gb_pprog.data.database.model.RoomModel

interface DatabaseRepository {

    fun insert(model: RoomModel)

    fun getAll(): List<RoomModel>
}