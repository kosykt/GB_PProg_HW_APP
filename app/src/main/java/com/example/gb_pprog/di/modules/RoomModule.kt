package com.example.gb_pprog.di.modules

import android.content.Context
import androidx.room.Room
import com.example.gb_pprog.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val DB_NAME = "database.db"

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder<AppDatabase?>(
            context,
            AppDatabase::class.java,
            DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}