package ru.kosykt.githubusers.data

import io.reactivex.rxjava3.core.Single
import ru.kosykt.githubusers.data.network.ApiHolder
import ru.kosykt.githubusers.data.network.models.NetworkOwner
import ru.kosykt.githubusers.data.network.models.NetworkUserDetailsModel
import ru.kosykt.githubusers.data.network.models.NetworkUsersModel
import ru.kosykt.githubusers.domain.GithubUsersRepository
import ru.kosykt.githubusers.domain.models.DomainOwner
import ru.kosykt.githubusers.domain.models.DomainUserDetailsModel
import ru.kosykt.githubusers.domain.models.DomainUserModel

class GithubUsersRepositoryImpl() : GithubUsersRepository {

    override fun getUsersList(): Single<List<DomainUserModel>> {
        return ApiHolder.retrofitService.getUsers().map { list ->
            list.toDomainUserModel()
        }
    }

    override fun getUserDetails(url: String): Single<List<DomainUserDetailsModel>> {
        return ApiHolder.retrofitService.getDetails(url).map { list ->
            list.toDomainUserDetailsModel()
        }
    }
}

fun List<NetworkUserDetailsModel>.toDomainUserDetailsModel() =
    this.map { it.toDomainUserDetailsModel() }

fun NetworkUserDetailsModel.toDomainUserDetailsModel() = DomainUserDetailsModel(
    id = this.id,
    name = this.name,
    owner = this.owner.toDomainOwner(),
    url = this.url,

)

fun NetworkOwner.toDomainOwner() = DomainOwner(
    id = id,
)

fun List<NetworkUsersModel>.toDomainUserModel() = this.map { it.toDomainUserModel() }

fun NetworkUsersModel.toDomainUserModel() = DomainUserModel(
    id = this.id,
    login = this.login,
    avatarUrl = this.avatar_url,
    reposUrl = this.repos_url
)