package ru.kosykt.mylibrary

import dagger.Component

@Component(modules = [TestModule::class])
interface TestComponent {

    fun inject(testFragment: TestFragment)
}