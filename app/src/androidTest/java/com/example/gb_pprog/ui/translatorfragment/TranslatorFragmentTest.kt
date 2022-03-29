package com.example.gb_pprog.ui.translatorfragment

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gb_pprog.R
import com.example.mytranslator.ui.translatorfragment.TranslatorFragment
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TranslatorFragmentTest {

    private lateinit var scenario: FragmentScenario<TranslatorFragment>

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
        onView(withId(R.id.fragment_translator)).check(matches(isDisplayed()))
    }

    @Test
    fun cardView_isDisplayed() {
        onView(withId(R.id.translator_cv_input)).check(matches(isDisplayed()))
    }

    @Test
    fun fab_isDisplayed() {
        onView(withId(R.id.translator_to_favorite_fab)).check(matches(isDisplayed()))
    }

    @Test
    fun textInputLayout_isDisplayed() {
        onView(withId(R.id.translator_til)).check(matches(isDisplayed()))
    }

    @Test
    fun textInputEditText_isDisplayed() {
        onView(withId(R.id.translator_tiet)).check(matches(isDisplayed()))
    }

    @Test
    fun recyclerView_isDisplayed() {
        onView(withId(R.id.translator_rv)).check(matches(isDisplayed()))
    }
}