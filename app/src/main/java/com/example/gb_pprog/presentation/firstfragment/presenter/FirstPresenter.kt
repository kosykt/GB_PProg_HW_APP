package com.example.gb_pprog.presentation.firstfragment.presenter

import android.util.Log
import moxy.MvpPresenter

class FirstPresenter(): MvpPresenter<FirstView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Log.d("testFirstPresenter", "success")
    }
}