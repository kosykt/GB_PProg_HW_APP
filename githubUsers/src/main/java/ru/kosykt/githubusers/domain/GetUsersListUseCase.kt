package ru.kosykt.githubusers.domain

class GetUsersListUseCase(
    private val repository: GithubUsersRepository
) {
    fun execute() = repository.getUsersList()
}