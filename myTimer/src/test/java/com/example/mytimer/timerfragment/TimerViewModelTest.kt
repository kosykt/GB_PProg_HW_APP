package com.example.mytimer.timerfragment

import com.example.gb_pprog.domain.MyTimerUseCase
import com.example.mytimer.di.TimerSubcomponentProvider
import com.example.mytimer.ui.timerfragment.TimerViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class TimerViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val myTimer = mock<MyTimerUseCase>()
    private val timerSubcomponentProvider = mock<TimerSubcomponentProvider>()

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Mockito.reset(myTimer)
        Mockito.reset(timerSubcomponentProvider)
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun should_return_notNull_ticker_initialValue() {
        runBlocking {
            val viewModel = TimerViewModel(myTimer, timerSubcomponentProvider)
            Assert.assertNotNull("ticker value is null", viewModel.ticker.value)
        }
    }

    @Test
    fun should_return_correct_ticker_initialValue() {
        runBlocking {
            val viewModel = TimerViewModel(myTimer, timerSubcomponentProvider)
            val expected = "00:00:000"
            val actual = viewModel.ticker.value
            Assert.assertEquals("ticker value is not correct", expected, actual)
        }
    }

    @Test
    fun should_return_notCorrect_ticker_initialValue() {
        runBlocking {
            val viewModel = TimerViewModel(myTimer, timerSubcomponentProvider)
            val expected = 00.00
            val actual = viewModel.ticker.value
            Assert.assertNotEquals("ticker value is not correct", expected, actual)
        }
    }

    @Test
    fun should_return_notNull_if_ticker_started(){
        runBlocking {
            val viewModel = TimerViewModel(myTimer, timerSubcomponentProvider)
            viewModel.start()
            Assert.assertNotNull("ticker value is null", viewModel.ticker.value)
        }
    }

    @Test
    fun should_return_notNull_if_ticker_paused(){
        runBlocking {
            val viewModel = TimerViewModel(myTimer, timerSubcomponentProvider)
            viewModel.pause()
            Assert.assertNotNull("ticker value is null", viewModel.ticker.value)
        }
    }

    @Test
    fun should_return_notNull_if_ticker_stopped(){
        runBlocking {
            val viewModel = TimerViewModel(myTimer, timerSubcomponentProvider)
            viewModel.stop()
            Assert.assertNotNull("ticker value is null", viewModel.ticker.value)
        }
    }

    @Test
    fun should_return_correct_time_if_ticker_stopped(){
        runBlocking {
            val viewModel = TimerViewModel(myTimer, timerSubcomponentProvider)
            viewModel.start()
            delay(500)
            viewModel.stop()
            val expected = "00:00:000"
            val actual = viewModel.ticker.value
            Assert.assertEquals("ticker value is not correct", expected, actual)
        }
    }
}