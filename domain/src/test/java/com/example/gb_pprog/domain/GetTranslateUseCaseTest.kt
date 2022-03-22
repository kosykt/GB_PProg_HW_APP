package com.example.gb_pprog.domain

import com.example.gb_pprog.domain.model.DomainMeaning
import com.example.gb_pprog.domain.model.DomainModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetTranslateUseCaseTest {

    private val domainRepository = mock<DomainRepository>()
    private val testInputWord = "test"

    @After
    fun tearDown() {
        Mockito.reset(domainRepository)
    }

    @Test
    fun should_return_notNull_data() {
        val useCase = GetTranslateUseCase(domainRepository)
        val testData = listOf<DomainModel>(DomainModel(101, listOf<DomainMeaning>(), "test"))
        runBlocking {
            Mockito.`when`(domainRepository.translate(testInputWord)).thenReturn(testData)
            Assert.assertNotNull("Return data is null", useCase.execute(testInputWord))
        }
    }

    @Test
    fun should_get_equals_data() {
        val useCase = GetTranslateUseCase(domainRepository)
        val testData = listOf<DomainModel>(DomainModel(101, listOf<DomainMeaning>(), "test"))
        runBlocking {
            Mockito.`when`(domainRepository.translate(testInputWord)).thenReturn(testData)
            Assert.assertEquals(
                "Return data is not equals",
                testData,
                useCase.execute(testInputWord)
            )
        }
    }

    @Test
    fun should_get_notEquals_data() {
        val useCase = GetTranslateUseCase(domainRepository)
        val testData = listOf<DomainModel>(DomainModel(111, listOf<DomainMeaning>(), "test"))
        runBlocking {
            val expected = listOf<DomainModel>(DomainModel(2, listOf<DomainMeaning>(), "2"))
            Mockito.`when`(domainRepository.translate(testInputWord)).thenReturn(testData)
            Assert.assertNotEquals(
                "Return data is not equals",
                expected,
                useCase.execute(testInputWord)
            )
        }
    }
}