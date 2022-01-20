package com.example.gb_pprog.data.repository

import com.example.gb_pprog.domain.DomainRepository
import com.example.gb_pprog.domain.model.DomainModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class DomainRepositoryImpl @Inject constructor(
    private val dataSource: DataSourceRepository
) : DomainRepository {

    override fun translate(word: String): Single<List<DomainModel>> {
        return dataSource.getData(word)
            .subscribeOn(Schedulers.io())
            .map { it.toListDomainModel() }
            .observeOn(AndroidSchedulers.mainThread())
    }
}