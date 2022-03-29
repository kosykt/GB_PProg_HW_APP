package com.example.gb_pprog.ui.timerfragment

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gb_pprog.R
import com.example.mytimer.ui.timerfragment.TimerFragment
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TimerFragmentTest {

    private lateinit var scenario: FragmentScenario<TimerFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_GB_PProg)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun test() {
        onView(withId(R.id.fragment_timer)).check(matches(isDisplayed()))
    }
}