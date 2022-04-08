package ru.kosykt.githubusers.domain

class GetUserDetailsUseCase(
    private val repository: GithubUsersRepository
) {
    fun execute(url: String) = repository.getUserDetails(url)
}