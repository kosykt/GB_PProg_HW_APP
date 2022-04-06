package ru.kosykt.githubusers.ui.usersfragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.kosykt.githubusers.di.GithubSubcomponentProvider
import ru.kosykt.githubusers.domain.DomainUserModel
import ru.kosykt.githubusers.domain.GetUsersListUseCase
import javax.inject.Inject

class UsersFragmentViewModel @Inject constructor(
    private val getUsersListUseCase: GetUsersListUseCase,
    private val githubSubcomponentProvider: GithubSubcomponentProvider,
) : ViewModel() {

    private var _users = MutableLiveData<List<DomainUserModel>>()
    val users: LiveData<List<DomainUserModel>> = _users

    init {
        getUsersListUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    _users.value = list
                },
                {
                    Log.e(this.javaClass.simpleName, "throwable: $it", it)
                }
            )
    }

    override fun onCleared() {
        githubSubcomponentProvider.destroySubcomponent()
        super.onCleared()
    }
}