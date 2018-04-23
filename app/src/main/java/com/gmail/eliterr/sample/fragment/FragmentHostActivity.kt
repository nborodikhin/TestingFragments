package com.gmail.eliterr.sample.fragment

import android.os.Bundle
import android.widget.FrameLayout
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity

class FragmentHostActivity : DaggerAppCompatActivity() {

    @dagger.Module()
    interface Module {
        @ContributesAndroidInjector(modules = arrayOf(MyFragment.Module::class))
        fun contribute(): FragmentHostActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(FrameLayout(this).apply {
            id = android.R.id.title
        })
        title = "Production fragment host"

        with(supportFragmentManager) {
            if (findFragmentByTag(FRAGMENT_TAG) == null) {
                beginTransaction()
                        .add(android.R.id.title, MyFragment(), FRAGMENT_TAG)
                        .commit()
            }
        }
    }

    companion object {
        private const val FRAGMENT_TAG = "fragment"
    }
}
