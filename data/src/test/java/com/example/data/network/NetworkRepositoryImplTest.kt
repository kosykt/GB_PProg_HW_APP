package com.example.data.network

import com.example.gb_pprog.data.network.NetworkRepositoryImpl
import com.example.gb_pprog.data.network.RetrofitService
import com.example.gb_pprog.data.network.model.Meaning
import com.example.gb_pprog.data.network.model.RetrofitTranslateDto
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class NetworkRepositoryImplTest {

    private val retrofitService = mock<RetrofitService>()

    @After
    fun tearDown() {
        Mockito.reset(retrofitService)
    }

    @Test
    fun should_return_notNull() {
        val networkRepositoryImpl = NetworkRepositoryImpl(retrofitService)
        val testData = listOf<RetrofitTranslateDto>(
            RetrofitTranslateDto(0, listOf<Meaning>(), "test1"),
            RetrofitTranslateDto(1, listOf<Meaning>(), "test2"),
            RetrofitTranslateDto(2, listOf<Meaning>(), "test3"),
        )
        runBlocking {
            Mockito.`when`(retrofitService.getNetworkData("test")).thenReturn(testData)
            val actual = networkRepositoryImpl.getData("test")
            Assert.assertNotNull("Return data is null", actual)
        }
    }

    @Test
    fun should_return_correct_data() {
        val networkRepositoryImpl = NetworkRepositoryImpl(retrofitService)
        val testData = listOf<RetrofitTranslateDto>(
            RetrofitTranslateDto(0, listOf<Meaning>(), "test1"),
            RetrofitTranslateDto(1, listOf<Meaning>(), "test2"),
            RetrofitTranslateDto(2, listOf<Meaning>(), "test3"),
        )
        runBlocking {
            Mockito.`when`(retrofitService.getNetworkData("test")).thenReturn(testData)
            val actual = networkRepositoryImpl.getData("test").toList()
            val expected = testData
            Assert.assertEquals("Return data is not equal to input data", expected, actual)
        }
    }

    @Test
    fun should_return_un_correct_data() {
        val networkRepositoryImpl = NetworkRepositoryImpl(retrofitService)
        val testData = listOf<RetrofitTranslateDto>(
            RetrofitTranslateDto(0, listOf<Meaning>(), "test1"),
            RetrofitTranslateDto(1, listOf<Meaning>(), "test2"),
            RetrofitTranslateDto(2, listOf<Meaning>(), "test3"),
        )
        runBlocking {
            Mockito.`when`(retrofitService.getNetworkData("test")).thenReturn(testData)
            val actual = networkRepositoryImpl.getData("test").toList()[0]
            val expected = listOf(RetrofitTranslateDto(0, listOf<Meaning>(), "test1"))
            Assert.assertNotEquals("Return data is equal to input data", expected, actual)
        }
    }
}