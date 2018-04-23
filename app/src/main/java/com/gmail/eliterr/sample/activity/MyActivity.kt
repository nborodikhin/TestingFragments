package com.gmail.eliterr.sample.activity

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import dagger.Binds
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MyActivity: DaggerAppCompatActivity() {
    interface NameGetter {
        fun getName(): String
    }

    class ProductionNameGetter @Inject constructor(): NameGetter {
        override fun getName() = "Production activity"
    }

    @dagger.Module
    interface Module {
        @Binds
        fun nameGetter(getter: ProductionNameGetter): NameGetter

        @ContributesAndroidInjector
        fun contribute(): MyActivity
    }

    @Inject
    lateinit var nameGetter: NameGetter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textView = TextView(this).apply {
            id = android.R.id.text1
            layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
            text = nameGetter.getName()
        }
        setContentView(textView)
    }
}
