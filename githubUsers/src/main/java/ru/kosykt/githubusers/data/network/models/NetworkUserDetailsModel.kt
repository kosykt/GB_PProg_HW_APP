package ru.kosykt.githubusers.data.network.models

data class NetworkUserDetailsModel(
    val id: String,
    val name: String,
    val url: String,
    val owner: NetworkOwner,
)

data class NetworkOwner(
    val id: Int,
)