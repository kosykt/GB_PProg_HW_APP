package com.example.myfirsttest.data

import com.example.myfirsttest.domain.UseCaseModel
import com.example.myfirsttest.domain.UseCaseRepository

class UseCaseRepositoryImpl() : UseCaseRepository {

    override fun getList(): List<UseCaseModel> {
        return listOf(
            UseCaseModel("one"),
            UseCaseModel("two"),
            UseCaseModel("three"),
            UseCaseModel("four"),
            UseCaseModel("five"),
        )
    }
}
