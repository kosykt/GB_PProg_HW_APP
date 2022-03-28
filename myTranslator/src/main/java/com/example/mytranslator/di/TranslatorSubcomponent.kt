package com.example.mytranslator.di

import com.example.mytranslator.ui.detailsfragment.DetailsFragment
import com.example.mytranslator.ui.favoritefragment.FavoriteFragment
import com.example.mytranslator.ui.translatorfragment.TranslatorFragment
import dagger.Subcomponent
import ru.kosykt.utils.di.scopes.TranslatorScope

@TranslatorScope
@Subcomponent(modules = [TranslatorModule::class])
interface TranslatorSubcomponent {

    fun inject(fragment: TranslatorFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: DetailsFragment)
}