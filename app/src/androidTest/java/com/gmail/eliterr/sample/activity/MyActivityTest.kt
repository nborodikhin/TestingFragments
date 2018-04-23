package com.gmail.eliterr.sample.activity

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.gmail.eliterr.sample.SampleApp
import com.gmail.eliterr.sample.activity.MyActivity.NameGetter
import org.junit.Rule
import org.junit.Test

class MyActivityTest {
    @get:Rule
    val rule = object: ActivityTestRule<MyActivity>(MyActivity::class.java) {
        val app = InstrumentationRegistry.getTargetContext().applicationContext as SampleApp

        override fun beforeActivityLaunched() =
            app.setTestActivityInjector(this@MyActivityTest::inject)

        override fun afterActivityFinished() =
            app.resetTestActivityInjector()
    }

    private fun inject(activity: MyActivity) {
        activity.nameGetter = object: NameGetter {
            override fun getName() = "TestText"
        }
    }

    @Test
    fun testActivityName() {
        onView(withId(android.R.id.text1)).check(matches(withText("TestText")))
    }
}