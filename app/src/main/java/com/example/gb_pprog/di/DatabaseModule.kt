package com.example.gb_pprog.di

import android.content.Context
import androidx.room.Room
import com.example.gb_pprog.data.database.AppDatabase
import org.koin.dsl.module

private const val DB_NAME = "words.db"

val databaseModule = module {

    single<AppDatabase> {
        Room.databaseBuilder<AppDatabase?>(
            get<Context>(),
            AppDatabase::class.java,
            DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}