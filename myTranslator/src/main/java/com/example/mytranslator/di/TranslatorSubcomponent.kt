package com.example.mytranslator.di

import com.example.gb_pprog.di.scopes.TranslatorScope
import com.example.mytranslator.ui.favoritefragment.FavoriteFragment
import com.example.mytranslator.ui.translatorfragment.TranslatorFragment
import dagger.Subcomponent

@TranslatorScope
@Subcomponent(modules = [TranslatorModule::class])
interface TranslatorSubcomponent {

    fun inject(fragment: TranslatorFragment)
    fun inject(fragment: FavoriteFragment)
}