package ru.kosykt.githubusers.ui.usersfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.kosykt.githubusers.domain.DomainUserModel
import ru.kosykt.githubusers.domain.GetUsersListUseCase

class UsersFragmentViewModel(
    private val getUsersListUseCase: GetUsersListUseCase
): ViewModel() {

    private var _users = MutableLiveData<List<DomainUserModel>>()
    val users: LiveData<List<DomainUserModel>> = _users

    init {
        _users.value = getUsersListUseCase.execute()
    }
}