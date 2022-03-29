package com.example.gb_pprog.ui.detailsfragment

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gb_pprog.R
import com.example.mytranslator.ui.detailsfragment.DetailsFragment
import com.example.mytranslator.ui.detailsfragment.DetailsModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest {

    private val model = DetailsModel(
        word = "word",
        transcription = "transcription",
        translation = "translation",
        note = "note",
        imageUrl = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png"
    )
    private val bundle = Bundle().apply {
        putParcelable("DetailsModel", model)
    }

    private lateinit var scenario: FragmentScenario<DetailsFragment>
    private val navController = TestNavHostController(
        ApplicationProvider.getApplicationContext()
    )

    @Before
    fun setup() {
        scenario = launchFragmentInContainer(
            themeResId = R.style.Theme_GB_PProg,
            fragmentArgs = bundle
        )
        scenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph_translator)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @Test
    fun fragment_isDisplayed() {
        onView(withId(R.id.fragment_details)).check(matches(isDisplayed()))
    }

    @Test
    fun cardView_isDisplayed() {
        onView(withId(R.id.details_cv)).check(matches(isDisplayed()))
    }

    @Test
    fun imageView_isDisplayed() {
        onView(withId(R.id.details_imageView)).check(matches(isDisplayed()))
    }

    @Test
    fun detailsText_isDisplayed() {
        onView(withId(R.id.details_text)).check(matches(isDisplayed()))
    }

    @Test
    fun detailsTranslation_isDisplayed() {
        onView(withId(R.id.details_translation)).check(matches(isDisplayed()))
    }

    @Test
    fun detailsTranscription_isDisplayed() {
        onView(withId(R.id.details_transcription)).check(matches(isDisplayed()))
    }

    @Test
    fun detailsNote_isDisplayed() {
        onView(withId(R.id.details_note)).check(matches(isDisplayed()))
    }

    @Test
    fun searchButton_isDisplayed() {
        onView(withId(R.id.details_search_btn)).check(matches(isDisplayed()))
    }

    @Test
    fun searchButton_text_click() {
        onView(withId(R.id.details_search_btn)).perform(click())
    }
}