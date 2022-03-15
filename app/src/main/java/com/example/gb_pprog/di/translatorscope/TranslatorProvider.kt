package com.example.gb_pprog.di.translatorscope

interface TranslatorProvider {

    fun initTranslatorSubcomponent(): TranslatorSubcomponent
    fun destroyTranslatorSubcomponent()
}