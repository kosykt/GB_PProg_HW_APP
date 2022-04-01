package com.example.gb_pprog.ui.mainactivity

import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gb_pprog.R
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityUITest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activity_fragmentContainer_AssertNotNull() {
        scenario.onActivity {
            TestCase.assertNotNull(it.findViewById(R.id.main_container))
        }
    }

    @Test
    fun activity_bottomNavView_AssertNotNull() {
        scenario.onActivity {
            TestCase.assertNotNull(it.findViewById(R.id.main_bnv))
        }
    }

    @Test
    fun activity_bottomNavView_IsDisplayed() {
        onView(withId(R.id.main_bnv)).check(matches(isDisplayed()))
    }

    @Test
    fun activity_bottomNavView_IsCompletelyDisplayed() {
        onView(withId(R.id.main_bnv)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun activity_bottomNavView_IsVisible() {
        onView(withId(R.id.main_bnv)).check((matches(withEffectiveVisibility(Visibility.VISIBLE))))
    }

    @Test
    fun activity_fragmentContainer_assertNotNull() {
        scenario.onActivity {
            TestCase.assertNotNull(it.findViewById<FragmentContainerView>(R.id.main_container))
        }
    }

    @Test
    fun activity_fragment_translator_IsDisplayed() {
        onView(withId(R.id.fragment_translator)).check(matches(isDisplayed()))
    }

    @Test
    fun activity_fragment_timer_IsDisplayed() {
        onView(withId(R.id.timerFragment)).perform(click())
        onView(withId(R.id.fragment_timer)).check(matches(isDisplayed()))
    }

    @Test
    fun activity_fragment_timer_pressBack() {
        onView(withId(R.id.timerFragment)).perform(click())
        onView(withId(R.id.main_activity)).perform(pressBack())
        onView(withId(R.id.fragment_translator)).check(matches(isDisplayed()))
    }

    @Test
    fun activity_fragment_favorite_IsDisplayed() {
        onView(withId(R.id.translator_to_favorite_fab)).perform(click())
        onView(withId(R.id.fragment_favorite)).check(matches(isDisplayed()))
    }

    @Test
    fun activity_fragment_favorite_pressBack() {
        onView(withId(R.id.translator_to_favorite_fab)).perform(click())
        onView(withId(R.id.main_activity)).perform(pressBack())
        onView(withId(R.id.fragment_translator)).check(matches(isDisplayed()))
    }
}