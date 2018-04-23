package com.gmail.eliterr.sample

import android.app.Activity
import android.support.annotation.VisibleForTesting
import com.gmail.eliterr.sample.activity.MyActivity
import com.gmail.eliterr.sample.fragment.FragmentHostActivity
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.AndroidInjector.Factory
import dagger.android.DispatchingAndroidInjector
import dagger.android.DispatchingAndroidInjector_Factory
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Provider

class SampleApp : DaggerApplication() {

    @Component(modules = arrayOf(
            AndroidSupportInjectionModule::class,
            FragmentHostActivity.Module::class,
            MyActivity.Module::class
    ))
    interface AppComponent: AndroidInjector<DaggerApplication> {
        fun inject(app: SampleApp)
    }

    private var activityInjector: DispatchingAndroidInjector<Activity>? = null

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityInjector ?: super.activityInjector()
    }

    @VisibleForTesting
    fun resetTestActivityInjector() {
        activityInjector = null
    }

    @VisibleForTesting
    fun setTestActivityInjector(clazz: Class<out Activity>, injector: AndroidInjector<in Activity>) {
        val factory = Factory<Activity> { _ -> AndroidInjector { injector.inject(it) } }
        val provider = Provider<Factory<out Activity>> { factory }
        val injectorFactories = mapOf(clazz to provider)

        activityInjector = DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(
                injectorFactories
        )
    }

    @VisibleForTesting
    inline fun <reified T : Activity> setTestActivityInjector(crossinline injector: (T) -> Unit) {
        setTestActivityInjector(T::class.java, AndroidInjector { if (it is T) injector(it) })
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerSampleApp_AppComponent.create()
    }
}
