package com.example.gb_pprog.di.modules.translator

import androidx.lifecycle.ViewModel
import com.example.gb_pprog.application.App
import com.example.gb_pprog.di.containers.FavoriteContainer
import com.example.gb_pprog.di.scopes.FavoriteScope
import com.example.gb_pprog.di.viewmodelsfactory.ViewModelKey
import com.example.gb_pprog.ui.favoritefragment.viewmodel.FavoriteViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FavoriteModule {

    @FavoriteScope
    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    fun bindFavoriteViewModel(favoriteViewModel: FavoriteViewModel): ViewModel

    @FavoriteScope
    @Binds
    fun provideFavoriteContainer(app: App): FavoriteContainer
}