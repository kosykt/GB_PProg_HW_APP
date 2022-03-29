package com.example.gb_pprog.ui.favoritefragment

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
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gb_pprog.R
import com.example.mytranslator.ui.favoritefragment.FavoriteFragment
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoriteFragmentTest {

    private lateinit var scenario: FragmentScenario<FavoriteFragment>
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
        onView(withId(R.id.fragment_favorite)).check(matches(isDisplayed()))
    }

    @Test
    fun fragment_rv_isDisplayed() {
        onView(withId(R.id.favorite_rv)).check(matches(isDisplayed()))
    }

    @Test
    fun fragment_rv_test() {
        onView(withId(R.id.favorite_rv))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click())
            )
    }

    @Test
    fun fragment_rv_item_delete_click_test() {
        onView(withId(R.id.favorite_rv))
            .perform(
                RecyclerViewActions
                    .actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        2,
                        tapOnItemWithId(R.id.favorite_item_delete)
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