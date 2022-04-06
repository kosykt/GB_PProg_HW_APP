package ru.kosykt.githubusers.data.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GithubRetrofitService {

    @GET("/users")
    fun getUsers(): Single<List<NetworkUsersModel>>
}