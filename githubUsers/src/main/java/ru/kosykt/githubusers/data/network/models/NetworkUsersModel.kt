package ru.kosykt.githubusers.data.network.models

import java.io.Serializable

data class NetworkUsersModel(
    val id: String,
    val login: String,
    val avatar_url: String,
    val repos_url: String
) : Serializable
