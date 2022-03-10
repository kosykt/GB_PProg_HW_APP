package com.example.gb_pprog.di.components

import com.example.gb_pprog.di.modules.*
import com.example.gb_pprog.mytimer.TimerViewModelFactory
import com.example.gb_pprog.presentation.favoritefragment.viewmodel.FavoriteViewModelFactory
import com.example.gb_pprog.presentation.mainactivity.ActivityViewModelFactory
import com.example.gb_pprog.presentation.translatorfragment.viewmodel.TranslatorViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        RoomModule::class,
        RetrofitModule::class,
        DataModule::class,
        DomainModule::class,
        AppModule::class,
        MyTimerModule::class,
    ]
)
interface AppComponent {

    fun injectActivityViewModelFactory(): ActivityViewModelFactory
    fun injectTranslatorViewModelFactory(): TranslatorViewModelFactory
    fun injectFavoriteViewModelFactory(): FavoriteViewModelFactory
    fun injectTimerViewModelFactory(): TimerViewModelFactory

}