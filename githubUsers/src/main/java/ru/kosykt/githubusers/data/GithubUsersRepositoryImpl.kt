package ru.kosykt.githubusers.data

import ru.kosykt.githubusers.domain.DomainUserModel
import ru.kosykt.githubusers.domain.GithubUsersRepository

class GithubUsersRepositoryImpl(): GithubUsersRepository {
    override fun getUsersList(): List<DomainUserModel> {
        return listOf(
            DomainUserModel(id = 1, name = "text"),
            DomainUserModel(id = 1, name = "text"),
            DomainUserModel(id = 1, name = "text"),
            DomainUserModel(id = 1, name = "text"),
            DomainUserModel(id = 1, name = "text"),
            DomainUserModel(id = 1, name = "text"),
        )
    }
}