package ru.kosykt.githubusers.di

interface GithubSubcomponentProvider {

    fun initSubcomponent(): GithubSubcomponent
    fun destroySubcomponent()
}