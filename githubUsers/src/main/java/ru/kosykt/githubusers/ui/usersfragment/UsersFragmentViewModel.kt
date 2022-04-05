package ru.kosykt.githubusers.ui.usersfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.kosykt.githubusers.di.GithubSubcomponentProvider
import ru.kosykt.githubusers.domain.DomainUserModel
import ru.kosykt.githubusers.domain.GetUsersListUseCase
import javax.inject.Inject

class UsersFragmentViewModel @Inject constructor(
    private val getUsersListUseCase: GetUsersListUseCase,
    private val githubSubcomponentProvider: GithubSubcomponentProvider,
): ViewModel() {

    private var _users = MutableLiveData<List<DomainUserModel>>()
    val users: LiveData<List<DomainUserModel>> = _users

    init {
        _users.value = getUsersListUseCase.execute()
    }

    override fun onCleared() {
        githubSubcomponentProvider.destroySubcomponent()
        super.onCleared()
    }
}