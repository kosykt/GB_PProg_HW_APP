package com.example.gb_pprog.presentation.firstfragment.presenter

import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface FirstView : MvpView {

    @AddToEndSingle
    fun getTranslateData(data: RetrofitTranslateDto)
}