package ru.kosykt.githubusers.ui.userdetailsfragment

import androidx.lifecycle.ViewModel
import ru.kosykt.githubusers.domain.GetUserDetailsUseCase
import javax.inject.Inject

class UserDetailsViewModel @Inject constructor(
    getUserDetailsUseCase: GetUserDetailsUseCase,
) : ViewModel() {

    val userDetails = getUserDetailsUseCase.execute("https://api.github.com/users/mojombo/repos")
}