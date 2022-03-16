package com.example.gb_pprog.presentation.mainactivity

import android.content.Context
import android.os.Build
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gb_pprog.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>
    private lateinit var context: Context

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        context = ApplicationProvider.getApplicationContext()
    }

    @After
    fun close() {
        /** без stopKoin() тесты все по очередно не выполняются. Вызывается исключение
         * с сообщением: "A Koin Application has already been started".
         * Но при использовании stopKoin() есть утечка в памяти(возможно),
         * System.logW: A resource was acquired at attached stack trace but never released.**/
        stopKoin()
        scenario.close()
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            TestCase.assertNotNull("Activity is null", it)
        }
    }

    @Test
    fun activity_IsResumed() {
        val expected = Lifecycle.State.RESUMED
        val actual = scenario.state
        TestCase.assertEquals("Activity lifecycle is not RESUMED", expected, actual)
    }

    @Test
    fun activityBottomNav_NotNull() {
        scenario.onActivity {
            val bottomNav = it.findViewById<BottomNavigationView>(R.id.main_bnv)
            TestCase.assertNotNull("BottomNav is Null", bottomNav)
        }
    }

    @Test
    fun activityBottomNav_Size() {
        scenario.onActivity {
            val bottomNav = it.findViewById<BottomNavigationView>(R.id.main_bnv)
            val expected = 4
            val actual = bottomNav.menu.size()
            TestCase.assertEquals("BottomNav menu size is not correct", expected, actual)
        }
    }

    @Test
    fun activityBottomNav_Is_Visible() {
        scenario.onActivity {
            val bottomNav = it.findViewById<BottomNavigationView>(R.id.main_bnv)
            val expected = View.VISIBLE
            val actual = bottomNav.visibility
            TestCase.assertEquals("BottomNav visibility is not correct", expected, actual)
        }
    }

    @Test
    fun activityFragmentContainerView_NotNull() {
        scenario.onActivity {
            val fragmentContainerView = it.findViewById<View>(R.id.main_container)
            TestCase.assertNotNull("FragmentContainerView is null", fragmentContainerView)
        }
    }

    @Test
    fun activityFragmentContainerView_IsVisible() {
        scenario.onActivity {
            val fragmentContainerView = it.findViewById<View>(R.id.main_container)
            val expected = View.VISIBLE
            val actual = fragmentContainerView.visibility
            TestCase.assertEquals("FragmentContainerView visibility is not correct",
                expected,
                actual)
        }
    }

    @Test
    fun correct_HomeFragment() {
        scenario.onActivity {
            val translatorFragment = it.findViewById<View>(R.id.fragment_translator)
            TestCase.assertNotNull("translatorFragment is null", translatorFragment)
        }
    }
}