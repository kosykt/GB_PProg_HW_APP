package com.example.gb_pprog.ui.timerfragment

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
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
    private val navController = TestNavHostController(
        ApplicationProvider.getApplicationContext()
    )

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_GB_PProg)
        scenario.onFragment { fragment ->
            navController.setGraph(R.navigation.main_navigation)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun fragment_isDisplayed() {
        onView(withId(R.id.fragment_timer)).check(matches(isDisplayed()))
    }

    @Test
    fun button_start_isDisplayed() {
        onView(withId(R.id.ft_start_btn)).check(matches(isDisplayed()))
    }

    @Test
    fun button_pause_isDisplayed() {
        onView(withId(R.id.ft_pause_btn)).check(matches(isDisplayed()))
    }

    @Test
    fun button_stop_isDisplayed() {
        onView(withId(R.id.ft_stop_btn)).check(matches(isDisplayed()))
    }

    @Test
    fun timer_textView_isDisplayed() {
        onView(withId(R.id.ft_timer_tv)).check(matches(isDisplayed()))
    }

    @Test
    fun timer_textView_initialValue() {
        onView(withId(R.id.ft_timer_tv)).check(matches(withText("00:00:000")))
    }

    @Test
    fun fragment_test_buttons() {
        onView(withId(R.id.ft_start_btn)).perform(click())
        onView(withId(R.id.ft_pause_btn)).perform(click())
        onView(withId(R.id.ft_stop_btn)).perform(click())
        onView(withId(R.id.ft_timer_tv)).check(matches(withText("00:00:000")))
    }
}