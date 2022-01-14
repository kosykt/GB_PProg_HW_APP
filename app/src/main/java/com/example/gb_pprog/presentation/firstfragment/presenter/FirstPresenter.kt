package com.example.gb_pprog.presentation.firstfragment.presenter

import android.util.Log
import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import com.example.gb_pprog.data.repository.DomainRepositoryImpl
import com.example.gb_pprog.domain.SearchWordUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class FirstPresenter(): MvpPresenter<FirstView>() {

    private val domainRepository = DomainRepositoryImpl()
    private val searchWordUseCase = SearchWordUseCase(domainRepository)

    fun translate(word: String){
        searchWordUseCase.execute(word)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { dto ->
                viewState.getTranslateData(dto[0].meanings[0].translation.text)
            }
    }

    private fun test(dto: RetrofitTranslateDto) {
        val asd = dto[0].meanings[0].translation.text
        Log.d("testRetrofit", asd)
    }

}