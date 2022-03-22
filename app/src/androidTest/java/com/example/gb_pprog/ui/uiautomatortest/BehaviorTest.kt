package com.example.gb_pprog.ui.uiautomatortest

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class BehaviorTest {

    private val uiDevice: UiDevice =
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val packageName = context.packageName
    private val intent = context.packageManager.getLaunchIntentForPackage(packageName)

    @Before
    fun setup() {
        uiDevice.pressHome()
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)
    }

    @Test
    fun test_device_notNull(){
        Assert.assertNotNull(uiDevice)
    }

    @Test
    fun test_app_package_notNull() {
        Assert.assertNotNull(packageName)
    }

    @Test
    fun test_mainActivity_intent_notNull() {
        Assert.assertNotNull(intent)
    }

    @Test
    fun test_startDestination() {
        val fragment = uiDevice.findObject(By.res(packageName, "fragment_translator"))
        Assert.assertNotNull(fragment)
    }

    @Test
    fun test_activity_bottomNavMenu_notNull() {
        val menu = uiDevice.findObject(By.res(packageName, "main_bnv"))
        Assert.assertNotNull(menu)
    }

    @Test
    fun test_behavior_translation() {
        val editText = uiDevice.findObject(By.res(packageName, "translator_tiet"))
        editText.text = "hello"
        val recycler = uiDevice.wait(
            Until.findObject(By.res(packageName, "translator_rv")),
            TIMEOUT
        )
        Assert.assertNotNull(recycler.childCount)
    }

    @Test
    fun test_behavior_TextInputEditText() {
        val editText = uiDevice.findObject(By.res(packageName, "translator_tiet"))
        editText.text = "hi"
        editText.click()
        uiDevice.pressDelete()
        Assert.assertEquals("h", editText.text)
    }

    @Test
    fun test_navigation_to_favorite() {
        val button = uiDevice.findObject(By.res(packageName, "translator_to_favorite_fab"))
        button.click()
        val fragment = uiDevice.findObject(By.res(packageName, "fragment_favorite"))
        Assert.assertNotNull(fragment)
    }

    @Test
    fun test_navigation_to_timer() {
        val button = uiDevice.findObject(By.res(packageName, "timerFragment"))
        button.click()
        val fragment = uiDevice.findObject(By.res(packageName, "fragment_timer"))
        Assert.assertNotNull(fragment)
    }

    @Test
    fun test_myTimer_textView_notNull() {
        uiDevice.findObject(By.res(packageName, "timerFragment")).click()
        val textView = uiDevice.findObject(By.res(packageName, "ft_timer_tv"))
        Assert.assertNotNull(textView)
    }

    @Test
    fun test_myTimer_textView_starting_value() {
        uiDevice.findObject(By.res(packageName, "timerFragment")).click()
        val textView = uiDevice.findObject(By.res(packageName, "ft_timer_tv"))
        Assert.assertEquals("00:00:000", textView.text)
    }

    @Test
    fun test_myTimer_behavior_on_start() {
        uiDevice.findObject(By.res(packageName, "timerFragment")).click()
        uiDevice.findObject(By.res(packageName, "ft_start_btn")).click()
        val textView = uiDevice.findObject(By.res(packageName, "ft_timer_tv"))
        Assert.assertNotEquals("00:00:000", textView.text)
    }

    @Test
    fun test_myTimer_behavior_on_stop() {
        uiDevice.findObject(By.res(packageName, "timerFragment")).click()
        uiDevice.findObject(By.res(packageName, "ft_start_btn")).click()
        uiDevice.findObject(By.res(packageName, "ft_stop_btn")).click()
        val textView = uiDevice.findObject(By.res(packageName, "ft_timer_tv"))
        Assert.assertEquals("00:00:000", textView.text)
    }

    companion object {
        private const val TIMEOUT = 5000L
    }
}