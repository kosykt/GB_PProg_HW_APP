package com.example.gb_pprog.presentation.firstfragment.presenter

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface FirstView : MvpView {

    @AddToEndSingle
    fun getTranslateData(word: String)
}