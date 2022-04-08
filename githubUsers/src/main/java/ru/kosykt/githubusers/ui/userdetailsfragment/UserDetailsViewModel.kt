package ru.kosykt.githubusers.ui.userdetailsfragment

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import ru.kosykt.githubusers.domain.GetUserDetailsUseCase
import ru.kosykt.githubusers.domain.models.DomainUserDetailsModel
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
) : ViewModel() {

    fun getDetails(url: String): Single<List<DomainUserDetailsModel>> {
        return getUserDetailsUseCase.execute(url)
    }
}