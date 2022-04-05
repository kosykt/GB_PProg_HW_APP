package ru.kosykt.githubusers.di

import android.app.Application
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.kosykt.githubusers.data.GithubUsersRepositoryImpl
import ru.kosykt.githubusers.domain.GetUsersListUseCase
import ru.kosykt.githubusers.domain.GithubUsersRepository
import ru.kosykt.githubusers.ui.usersfragment.UsersFragmentViewModel
import ru.kosykt.utils.di.scopes.GithubScope
import ru.kosykt.utils.di.viewmodelsfactory.ViewModelKey

@Module
interface GithubModule {

    @GithubScope
    @Binds
    @IntoMap
    @ViewModelKey(UsersFragmentViewModel::class)
    fun bindTranslatorViewModel(vm: UsersFragmentViewModel): ViewModel

    companion object {

        @GithubScope
        @Provides
        fun provideGithubUsersRepository(): GithubUsersRepository {
            return GithubUsersRepositoryImpl()
        }

        @GithubScope
        @Provides
        fun provideGetUsersListUseCase(
            githubUsersRepository: GithubUsersRepository
        ): GetUsersListUseCase {
            return GetUsersListUseCase(githubUsersRepository)
        }

        @GithubScope
        @Provides
        fun provideTranslatorProvider(application: Application): GithubSubcomponentProvider {
            return (application as GithubSubcomponentProvider)
        }
    }
}