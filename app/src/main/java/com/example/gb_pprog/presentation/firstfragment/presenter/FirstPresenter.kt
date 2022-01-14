package com.example.gb_pprog.presentation.firstfragment.presenter

import com.example.gb_pprog.data.network.ApiHolder
import com.example.gb_pprog.data.repository.DomainRepositoryImpl
import com.example.gb_pprog.domain.SearchWordUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class FirstPresenter() : MvpPresenter<FirstView>() {

    private val retrofitService = ApiHolder.retrofitService
    private val domainRepository = DomainRepositoryImpl(retrofitService)
    private val searchWordUseCase = SearchWordUseCase(domainRepository)

    fun translate(word: String) {
        searchWordUseCase.execute(word)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { dto ->
                viewState.getTranslateData(dto)
            }
    }
}