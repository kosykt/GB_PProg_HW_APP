package com.example.gb_pprog.di.modules.singletons

import androidx.room.Room
import com.example.gb_pprog.application.App
import com.example.gb_pprog.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val DB_NAME = "database.db"

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(app: App): AppDatabase {
        return Room.databaseBuilder<AppDatabase?>(
            app.applicationContext,
            AppDatabase::class.java,
            DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}