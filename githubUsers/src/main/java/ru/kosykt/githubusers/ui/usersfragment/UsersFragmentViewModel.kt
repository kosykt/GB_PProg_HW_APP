package ru.kosykt.githubusers.ui.usersfragment

import androidx.lifecycle.ViewModel
import ru.kosykt.githubusers.di.GithubSubcomponentProvider
import ru.kosykt.githubusers.domain.GetUsersListUseCase
import javax.inject.Inject

class UsersFragmentViewModel @Inject constructor(
    getUsersListUseCase: GetUsersListUseCase,
    private val githubSubcomponentProvider: GithubSubcomponentProvider,
) : ViewModel() {

    val users = getUsersListUseCase.execute()

    override fun onCleared() {
        githubSubcomponentProvider.destroySubcomponent()
        super.onCleared()
    }
}