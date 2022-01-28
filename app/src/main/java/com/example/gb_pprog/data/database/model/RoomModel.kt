package com.example.gb_pprog.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomModel(
    @PrimaryKey
    val word: String
)
