package com.example.gb_pprog.domain

import com.example.gb_pprog.domain.model.FavoriteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetAllFavoritesUseCaseTest {

    private val domainRepository = mock<DomainRepository>()

    @After
    fun tearDown() {
        Mockito.reset(domainRepository)
    }

    @Test
    fun should_return_notNull_data(){
        val useCase = GetAllFavoritesUseCase(domainRepository)
        val testData: Flow<List<FavoriteModel>> = flowOf(
            listOf(
                FavoriteModel("test1", "data1"),
                FavoriteModel("test2", "data2"),
                FavoriteModel("test3", "data3"),
            )
        )
        Mockito.`when`(domainRepository.getAllFavorite()).thenReturn(testData)
        Assert.assertNotNull("Return data is null", useCase.execute())
    }

    @Test
    fun should_get_equals_data(){
        val useCase = GetAllFavoritesUseCase(domainRepository)
        val testData: Flow<List<FavoriteModel>> = flowOf(
            listOf(
                FavoriteModel("test1", "data1"),
                FavoriteModel("test2", "data2"),
                FavoriteModel("test3", "data3"),
            )
        )
        Mockito.`when`(domainRepository.getAllFavorite()).thenReturn(testData)
        val actual = useCase.execute()
        val expected = testData
        Assert.assertEquals("Return data is not equal to input data", expected, actual)
    }

    @Test
    fun should_get_notEquals_data(){
        val useCase = GetAllFavoritesUseCase(domainRepository)
        val testData: Flow<List<FavoriteModel>> = flowOf(
            listOf(
                FavoriteModel("test1", "data1"),
                FavoriteModel("test2", "data2"),
                FavoriteModel("test3", "data3"),
            )
        )
        Mockito.`when`(domainRepository.getAllFavorite()).thenReturn(testData)
        val actual = useCase.execute()
        val expected = flowOf(listOf(FavoriteModel("test1", "data1")))
        Assert.assertNotEquals("Return data is not equal to input data", expected, actual)
    }
}