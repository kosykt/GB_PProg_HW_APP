package ru.kosykt.githubusers.ui.usersfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.kosykt.githubusers.domain.GetUsersListUseCase
import java.lang.RuntimeException

class UsersFragmentViewModelFactory(
    private val useCase: GetUsersListUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersFragmentViewModel::class.java)) {
            return UsersFragmentViewModel(useCase) as T
        } else {
            throw RuntimeException("UsersFragmentViewModelFactory: ViewModel class - $modelClass")
        }
    }
}