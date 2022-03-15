package com.example.gb_pprog.di.translatorscope

import com.example.gb_pprog.di.scopes.TranslatorScope
import com.example.gb_pprog.di.translatorscope.TranslatorModule
import com.example.gb_pprog.ui.favoritefragment.FavoriteFragment
import com.example.gb_pprog.ui.translatorfragment.TranslatorFragment
import dagger.Subcomponent

@TranslatorScope
@Subcomponent(modules = [TranslatorModule::class])
interface TranslatorSubcomponent {

    fun inject(fragment: TranslatorFragment)
    fun inject(fragment: FavoriteFragment)
}