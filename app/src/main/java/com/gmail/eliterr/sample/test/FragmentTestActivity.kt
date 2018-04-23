package com.gmail.eliterr.sample.test

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector

class FragmentTestActivity : AppCompatActivity(), HasSupportFragmentInjector {
    private lateinit var injector: AndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = injector

    fun startFragment(fragment: Fragment, injector: AndroidInjector<Fragment>) {
        this.injector = injector
        supportFragmentManager. beginTransaction()
                .add(android.R.id.content, fragment, "TAG")
                .commit()
    }

    inline fun <reified T : Fragment> startFragment(fragment: T, crossinline injector: (T) -> Unit) {
        startFragment(fragment, AndroidInjector { if (it is T) injector(it) })
    }

}
