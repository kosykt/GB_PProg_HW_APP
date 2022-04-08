package ru.kosykt.githubusers.domain.models

import java.io.Serializable

data class DomainUserModel(
    val id: String,
    val login: String,
    val avatarUrl: String,
    val reposUrl: String
) : Serializable
