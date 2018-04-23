package com.gmail.eliterr.sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import dagger.Binds
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MyFragment : DaggerFragment() {

    interface NameGetter {
        fun getName(): String
    }

    class ProductionNameGetter @Inject constructor(): NameGetter {
        override fun getName() = "Production fragment"
    }

    @dagger.Module
    interface Module {
        @Binds
        fun nameGetter(getter: ProductionNameGetter): NameGetter

        @ContributesAndroidInjector
        fun contribute(): MyFragment
    }

    @Inject
    lateinit var nameGetter: NameGetter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return TextView(context).apply {
            id = android.R.id.title
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title: TextView = view.findViewById(android.R.id.title)
        title.text = nameGetter.getName()
    }
}
