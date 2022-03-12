package com.example.gb_pprog.di.containers

import com.example.gb_pprog.di.components.FavoriteSubcomponent

interface FavoriteContainer {

    fun initFavoriteSubcomponent(): FavoriteSubcomponent

    fun destroyFavoriteSubcomponent()
}