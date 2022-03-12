package com.example.gb_pprog.di.modules.singletons

import com.example.gb_pprog.data.network.RetrofitService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


private const val BASE_URL = "https://dictionary.skyeng.ru/api/public/v1/"

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): RetrofitService {
        return retrofit.create(RetrofitService::class.java)
    }
}