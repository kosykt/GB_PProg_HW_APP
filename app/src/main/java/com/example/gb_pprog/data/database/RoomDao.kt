package com.example.gb_pprog.data.database

import androidx.room.*
import com.example.gb_pprog.data.database.model.RoomModel

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: RoomModel)

    @Query("SELECT * FROM RoomModel")
    suspend fun getAll(): List<RoomModel>

    @Delete
    suspend fun delete(model: RoomModel)
}