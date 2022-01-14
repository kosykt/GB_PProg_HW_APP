package com.example.gb_pprog.presentation.firstfragment.presenter

import com.example.gb_pprog.domain.model.DomainModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface FirstView : MvpView {

    @AddToEndSingle
    fun getTranslateData(data: DomainModel)
}