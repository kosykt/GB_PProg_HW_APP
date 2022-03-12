package com.example.gb_pprog.di.modules.singletons

import com.example.gb_pprog.application.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Singleton
    @Provides
    fun provideApplication(): App {
        return app
    }
}