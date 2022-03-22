package com.example.gb_pprog.ui.uiautomatortest

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class InitialTest {

    private val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val packageName = context.packageName
    private val intent = context.packageManager.getLaunchIntentForPackage(packageName)

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
}