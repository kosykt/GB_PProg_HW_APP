package ru.kosykt.githubusers.domain

interface GithubUsersRepository {

    fun getUsersList(): List<DomainUserModel>
}