package ru.kosykt.githubusers.di

import dagger.Subcomponent
import ru.kosykt.githubusers.ui.userdetailsfragment.UserDetailsFragment
import ru.kosykt.githubusers.ui.usersfragment.UsersFragment
import ru.kosykt.utils.di.scopes.GithubScope

@GithubScope
@Subcomponent(modules = [GithubModule::class])
interface GithubSubcomponent {

    fun inject(fragment: UsersFragment)
    fun inject(fragment: UserDetailsFragment)
}