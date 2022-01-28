package com.example.gb_pprog.data.database

import com.example.gb_pprog.data.database.model.RoomModel
import com.example.gb_pprog.data.repository.DatabaseRepository

class DatabaseRepositoryImpl(
    private val db: AppDatabase
): DatabaseRepository {

    override fun insert(model: RoomModel) {
        db.roomDao().insert(model)
    }
}