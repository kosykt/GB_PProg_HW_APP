package com.example.gb_pprog.di.components

import com.example.gb_pprog.di.modules.translator.FavoriteModule
import com.example.gb_pprog.di.scopes.FavoriteScope
import com.example.gb_pprog.di.viewmodelsfactory.ViewModelFactory
import dagger.Subcomponent

@FavoriteScope
@Subcomponent(modules = [FavoriteModule::class])
interface FavoriteSubcomponent {

    fun injectFavoriteViewModelFactory(): ViewModelFactory
}