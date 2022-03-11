package com.example.gb_pprog.di.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gb_pprog.mytimer.TimerViewModel
import com.example.gb_pprog.presentation.favoritefragment.viewmodel.FavoriteViewModel
import com.example.gb_pprog.presentation.mainactivity.ActivityViewModel
import com.example.gb_pprog.presentation.translatorfragment.viewmodel.TranslatorViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ActivityViewModel::class)
    fun bindMainViewModel(mainViewModel: ActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TranslatorViewModel::class)
    fun bindTranslatorViewModel(translatorViewModel: TranslatorViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    fun bindFavoriteViewModel(favoriteViewModel: FavoriteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TimerViewModel::class)
    fun bindTimerViewModel(timerViewModel: TimerViewModel): ViewModel
}