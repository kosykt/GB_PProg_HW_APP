package com.example.gb_pprog.domain

import com.example.gb_pprog.domain.model.FavoriteModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class DeleteFavoriteUseCaseTest {

    private val domainRepository = mock<DomainRepository>()

    @After
    fun tearDown() {
        Mockito.reset(domainRepository)
    }

    @Test
    fun verify() {
        val useCase = DeleteFavoriteUseCase(domainRepository)
        val favoriteModel = FavoriteModel("test", "test")
        runBlocking {
            useCase.execute(favoriteModel)
            Mockito.verify(domainRepository).deleteFavorite(favoriteModel)
        }
    }
}