package ru.kosykt.githubusers.domain

import io.reactivex.rxjava3.core.Single
import ru.kosykt.githubusers.data.network.NetworkUsersModel

interface GithubUsersRepository {

    fun getUsersList(): Single<List<DomainUserModel>>
}