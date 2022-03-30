package com.example.mytranslator.di

interface TranslatorSubcomponentProvider {

    fun initTranslatorSubcomponent(): TranslatorSubcomponent
    fun destroyTranslatorSubcomponent()
}