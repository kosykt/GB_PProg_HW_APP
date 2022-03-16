package com.example.myfirsttest.domain

interface UseCaseRepository {

    fun getList(): List<UseCaseModel>
}