package com.example.mytranslator.di

interface TranslatorProvider {

    fun initTranslatorSubcomponent(): TranslatorSubcomponent
    fun destroyTranslatorSubcomponent()
}