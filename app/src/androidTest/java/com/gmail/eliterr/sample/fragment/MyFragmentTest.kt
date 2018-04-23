package com.gmail.eliterr.sample.fragment

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import com.gmail.eliterr.sample.test.FragmentTestActivity
import org.junit.Rule
import org.junit.Test

class MyFragmentTest {
    val fragment = MyFragment()

    @get:Rule
    val rule = object: ActivityTestRule<FragmentTestActivity>(FragmentTestActivity::class.java) {
        override fun afterActivityLaunched() = runOnUiThread {
            activity.startFragment(fragment, this@MyFragmentTest::inject)
        }
    }

    fun inject(fragment: MyFragment) {
        fragment.nameGetter = object: MyFragment.NameGetter {
            override fun getName() = "Test fragment"
        }
    }

    @Test
    fun testFragmentName() {
        onView(withId(android.R.id.title)).check(matches(withText("Test fragment")))
    }
}