package ru.kosykt.githubusers.domain.models

import java.io.Serializable

data class DomainUserDetailsModel(
    val id: String,
    val name: String,
    val url: String,
    val owner: DomainOwner,
) : Serializable

data class DomainOwner(
    val id: Int,
) : Serializable