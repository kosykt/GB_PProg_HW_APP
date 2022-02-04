package com.example.gb_pprog.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gb_pprog.data.database.model.RoomModel

@Database(entities = [RoomModel::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun roomDao(): RoomDao
}