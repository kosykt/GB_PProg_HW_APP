package com.example.gb_pprog.ui.favoritefragment

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gb_pprog.R
import com.example.mytranslator.ui.favoritefragment.FavoriteFragment
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoriteFragmentTest {

    private lateinit var scenario: FragmentScenario<FavoriteFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_GB_PProg)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun fragment_isDisplayed() {
        onView(withId(R.id.fragment_favorite)).check(matches(isDisplayed()))
    }

    @Test
    fun fragment_rv_isDisplayed() {
        onView(withId(R.id.favorite_rv)).check(matches(isDisplayed()))
    }
}