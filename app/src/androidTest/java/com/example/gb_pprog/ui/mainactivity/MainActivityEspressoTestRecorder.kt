package com.example.gb_pprog.ui.mainactivity

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.gb_pprog.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**Не может корректно работать со списками
 * избыточный код
 * тяжело поддерживать
 * сложно понять что вообще происходит
 * использует устаревший AndroidJUnit4::class*/
@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTestRecorder {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityEspressoTestRecorder() {
        val textInputEditText = onView(
            allOf(
                withId(R.id.translator_tiet),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.translator_til),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("hi"), closeSoftKeyboard())

        val materialCheckBox = onView(
            allOf(
                withId(R.id.translator_item_favorite_btn),
                childAtPosition(
                    allOf(
                        withId(R.id.translator_constraint),
                        childAtPosition(
                            withClassName(`is`("androidx.cardview.widget.CardView")),
                            0
                        )
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        materialCheckBox.perform(click())

        val floatingActionButton = onView(
            allOf(
                withId(R.id.translator_to_favorite_fab),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.translator_cv_input),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        val imageFilterButton = onView(
            allOf(
                withId(R.id.favorite_item_delete),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.cardview.widget.CardView")),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        imageFilterButton.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
