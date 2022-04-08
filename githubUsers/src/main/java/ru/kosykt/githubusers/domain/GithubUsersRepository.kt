package ru.kosykt.githubusers.domain

import io.reactivex.rxjava3.core.Single
import ru.kosykt.githubusers.domain.models.DomainUserDetailsModel
import ru.kosykt.githubusers.domain.models.DomainUserModel

interface GithubUsersRepository {

    fun getUsersList(): Single<List<DomainUserModel>>

    fun getUserDetails(url: String): Single<List<DomainUserDetailsModel>>
}