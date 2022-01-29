package com.example.gb_pprog.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gb_pprog.data.database.model.RoomModel

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: RoomModel)

    @Query("SELECT * FROM RoomModel")
    fun getAll(): List<RoomModel>
}