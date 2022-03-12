package com.example.gb_pprog.di.modules.singletons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gb_pprog.di.viewmodelsfactory.ViewModelFactory
import com.example.gb_pprog.di.viewmodelsfactory.ViewModelKey
import com.example.gb_pprog.ui.favoritefragment.viewmodel.FavoriteViewModel
import com.example.gb_pprog.ui.mainactivity.ActivityViewModel
import com.example.gb_pprog.ui.timerfragment.TimerViewModel
import com.example.gb_pprog.ui.translatorfragment.viewmodel.TranslatorViewModel
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
}