package com.example.gb_pprog.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.kosykt.utils.imageloader.GlideImageLoader
import ru.kosykt.utils.imageloader.ImageLoader
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return app
    }

    @Singleton
    @Provides
    fun provideGlide(): ImageLoader {
        return GlideImageLoader()
    }
}