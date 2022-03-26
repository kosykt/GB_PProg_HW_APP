package com.example.data.network

import com.example.gb_pprog.data.network.RetrofitService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServiceTest : MockWebServerTest() {

    private lateinit var service: RetrofitService

    @Before
    fun setup() {
        val baseUrl = mockWebServer.url("/")
        service = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }

    @Test
    fun api_service_response_notNull() {
        enqueue("translate_hello.json")
        runBlocking {
            val apiResponse = service.getNetworkData("hello")
            Assert.assertNotNull("response is null", apiResponse)
        }
    }

    @Test
    fun api_service_response_notEmpty() {
        enqueue("translate_hello.json")
        runBlocking {
            val apiResponse = service.getNetworkData("hello")
            Assert.assertTrue("response is empty", apiResponse.isNotEmpty())
        }
    }

    @Test
    fun api_service_response_id_is_correct() {
        enqueue("translate_hello.json")
        runBlocking {
            val apiResponse = service.getNetworkData("hello")
            val expected = 1949
            val actual = apiResponse[0].id
            Assert.assertEquals("response id is not correct", expected, actual)
        }
    }

    @Test
    fun api_service_response_id_is_unCorrect() {
        enqueue("translate_hi.json")
        runBlocking {
            val apiResponse = service.getNetworkData("hello")
            val expected = 1949
            val actual = apiResponse[0].id
            Assert.assertNotEquals("response id is not correct", expected, actual)
        }
    }
}