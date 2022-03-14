package ru.kosykt.mylibrary

import com.example.gb_pprog.domain.TestUseCase
import dagger.Module
import dagger.Provides

@Module
class TestModule() {

    @Provides
    fun provideTestUseCase(): TestUseCase {
        return TestUseCase()
    }
}