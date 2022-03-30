package com.example.gb_pprog.di.components

import com.example.gb_pprog.di.modules.*
import com.example.mytranslator.di.TranslatorSubcomponent
import com.example.mytimer.di.TimerSubcomponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RoomModule::class,
        RetrofitModule::class,
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class,
    ]
)
interface AppComponent {

    fun provideTranslatorSubcomponent(): TranslatorSubcomponent
    fun provideTimerSubcomponent(): TimerSubcomponent
}