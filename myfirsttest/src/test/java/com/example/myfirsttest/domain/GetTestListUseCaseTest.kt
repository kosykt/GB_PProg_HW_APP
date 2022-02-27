package com.example.myfirsttest.domain

import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetTestListUseCaseTest {

    private val useCaseRepository = mock<UseCaseRepository>()

    @After
    fun tearDown() {
        Mockito.reset(useCaseRepository)
    }


    @Test
    fun should_return_correct_data() {
        val useCaseTest = GetListUseCase(useCaseRepository)
        val testData = listOf(
            UseCaseModel("one"),
            UseCaseModel("two"),
            UseCaseModel("three"),
        )
        Mockito.`when`(useCaseRepository.getList()).thenReturn(testData)
        val actual = useCaseTest.execute()
        val expected = testData

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun should_return_un_correct_data() {
        val useCaseTest = GetListUseCase(useCaseRepository)
        val testData = listOf(
            UseCaseModel("one"),
            UseCaseModel("two"),
            UseCaseModel("three"),
        )
        Mockito.`when`(useCaseRepository.getList()).thenReturn(testData)
        val actual = useCaseTest.execute()
        val expected = listOf(UseCaseModel("one"))

        Assert.assertNotEquals(expected, actual)
    }

    @Test
    fun should_mockito_verify_current_times() {
        val useCaseTest = GetListUseCase(useCaseRepository)
        useCaseTest.execute()
        Mockito.verify(useCaseRepository, Mockito.times(1)).getList()
    }
}