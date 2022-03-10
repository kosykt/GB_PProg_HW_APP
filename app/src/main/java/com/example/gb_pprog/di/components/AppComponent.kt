package com.example.gb_pprog.di.components

import com.example.gb_pprog.di.modules.*
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
        AppModule::class
    ]
)
interface AppComponent {

    fun injectTranslatorViewModelFactory(): TranslatorViewModelFactory
    fun injectActivityViewModelFactory(): ActivityViewModelFactory
}