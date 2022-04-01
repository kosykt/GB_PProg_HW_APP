package com.example.gb_pprog.domain

import com.example.gb_pprog.domain.model.DomainModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class SaveFavoriteUseCaseTest {

    private val domainRepository = mock<DomainRepository>()

    @After
    fun tearDown() {
        Mockito.reset(domainRepository)
    }

    @Test
    fun verify() {
        val useCase = SaveFavoriteUseCase(domainRepository)
        val domainModel = DomainModel(1, listOf(), "test")
        runBlocking {
            useCase.execute(domainModel)
            Mockito.verify(domainRepository).saveFavorite(domainModel)
        }
    }
}