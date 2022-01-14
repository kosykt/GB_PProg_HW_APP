package com.example.gb_pprog.domain

import com.example.gb_pprog.domain.model.DomainModel
import io.reactivex.rxjava3.core.Single

interface DomainRepository {

    fun translate(word: String): Single<List<DomainModel>>
}