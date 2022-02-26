package com.example.myfirsttest.domain

import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetTestListUseCaseTest {

    private val useCaseRepository = mock<UseCaseRepository>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(useCaseRepository)
    }


    @Test
    fun shouldReturnCorrectData() {
        val useCaseTest = GetListUseCase(useCaseRepository)
        val testData = listOf(
            UseCaseModel("one"),
            UseCaseModel("two"),
            UseCaseModel("three"),
        )
        Mockito.`when`(useCaseRepository.getList()).thenReturn(testData)
        val actual = useCaseTest.execute()
        val expected = testData

        Assertions.assertEquals(actual, expected)
    }

    @Test
    fun shouldReturnUnCorrectData() {
        val useCaseTest = GetListUseCase(useCaseRepository)
        val testData = listOf(
            UseCaseModel("one"),
            UseCaseModel("two"),
            UseCaseModel("three"),
        )
        Mockito.`when`(useCaseRepository.getList()).thenReturn(testData)
        val actual = useCaseTest.execute()
        val expected = listOf(UseCaseModel("one"))

        Assertions.assertNotEquals(actual, expected)
    }

    @Test
    fun shouldMockitoVerifyCurrentTimes() {
        val useCaseTest = GetListUseCase(useCaseRepository)
        useCaseTest.execute()
        Mockito.verify(useCaseRepository, Mockito.times(1)).getList()
    }
}