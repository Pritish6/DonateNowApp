package com.drivertest.donatenowapp

import android.app.Application
import com.drivertest.donatenowapp.di.component.ApplicationComponent
import com.drivertest.donatenowapp.di.component.DaggerApplicationComponent
import com.drivertest.donatenowapp.di.module.ApplicationModule


class AppClass:Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    // Needed to replace the component with a test specific one
    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }
}