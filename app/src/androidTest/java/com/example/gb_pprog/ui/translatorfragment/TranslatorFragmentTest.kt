package com.example.gb_pprog.ui.translatorfragment

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gb_pprog.R
import com.example.mytranslator.ui.translatorfragment.TranslatorFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TranslatorFragmentTest {

    private lateinit var scenario: FragmentScenario<TranslatorFragment>
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

    @Test
    fun fragment_rv_test() {
        onView(withId(R.id.translator_tiet)).perform(click())
        onView(withId(R.id.translator_tiet))
            .perform(replaceText("hello"), closeSoftKeyboard())
        runBlocking { delay(1000) }
        onView(withId(R.id.translator_rv))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click())
            )
    }

    @Test
    fun fragment_rv_item_add_to_favorite_click_test() {
        onView(withId(R.id.translator_tiet)).perform(click())
        onView(withId(R.id.translator_tiet))
            .perform(replaceText("hello"), closeSoftKeyboard())
        runBlocking { delay(1000) }
        onView(withId(R.id.translator_rv))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        2,
                        tapOnItemWithId(R.id.translator_item_favorite_btn)
                    )
            )
    }

    private fun tapOnItemWithId(id: Int) = object : ViewAction {
        override fun getConstraints(): Matcher<View>? {
            return null
        }

        override fun getDescription(): String {
            return "Нажимаем на view с указанным id"
        }

        override fun perform(uiController: UiController, view: View) {
            val v = view.findViewById(id) as View
            v.performClick()
        }
    }
}