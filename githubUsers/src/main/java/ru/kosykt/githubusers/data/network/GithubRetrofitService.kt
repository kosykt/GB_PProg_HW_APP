package ru.kosykt.githubusers.data.network

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url
import ru.kosykt.githubusers.data.network.models.NetworkUserDetailsModel
import ru.kosykt.githubusers.data.network.models.NetworkUsersModel

interface GithubRetrofitService {

    @GET("/users")
    fun getUsers(): Single<List<NetworkUsersModel>>

    @GET
    fun getDetails(@Url url: String): Single<List<NetworkUserDetailsModel>>
}