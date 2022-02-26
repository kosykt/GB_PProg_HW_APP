package com.example.myfirsttest.domain

import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetTestListUseCaseTest {

    private val useCaseRepository = mock<UseCaseRepository>()
    private val useCaseTest = GetListUseCase(useCaseRepository)
    private val testData = listOf(
        UseCaseModel("one"),
        UseCaseModel("two"),
        UseCaseModel("three"),
    )


    @Test
    fun shouldReturnCorrectData(){
        Mockito.`when`(useCaseRepository.getList()).thenReturn(testData)
        val actual = useCaseTest.execute()
        val expected = testData

        Assert.assertEquals(actual, expected)
    }

    @Test
    fun shouldReturnUnCorrectData(){
        Mockito.`when`(useCaseRepository.getList()).thenReturn(testData)
        val actual = useCaseTest.execute()
        val expected = listOf(UseCaseModel("one"))

        Assert.assertNotEquals(actual, expected)
    }
}