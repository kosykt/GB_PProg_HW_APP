package com.example.gb_pprog.ui.detailsfragment

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gb_pprog.R
import com.example.mytranslator.ui.detailsfragment.DetailsFragment
import com.example.mytranslator.ui.detailsfragment.DetailsFragmentArgs
import com.example.mytranslator.ui.detailsfragment.DetailsModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest {

    private lateinit var scenario: FragmentScenario<DetailsFragment>
    private val navController = TestNavHostController(
        ApplicationProvider.getApplicationContext()
    )
    private val model = DetailsModel(
        word = "word",
        transcription = "transcription",
        translation = "translation",
        note = "note",
        imageUrl = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_92x30dp.png"
    )
    private val bundle = Bundle().apply {
        putParcelable("10", model)
    }

    @Before
    fun setup() {
//        TODO("необходимо передать не fragmentArgs, а navArgs")
        scenario = launchFragmentInContainer(
            themeResId = R.style.Theme_GB_PProg
        )
        scenario.onFragment { fragment ->
            navController.setGraph(R.navigation.main_navigation)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @Test
    fun fragment_isDisplayed() {
        onView(withId(R.id.fragment_details)).check(matches(isDisplayed()))
    }
}