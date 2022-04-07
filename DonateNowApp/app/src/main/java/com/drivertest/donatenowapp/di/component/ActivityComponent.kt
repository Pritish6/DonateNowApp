package com.drivertest.donatenowapp.di.component

import com.drivertest.donatenowapp.di.ActivityScope
import com.drivertest.donatenowapp.di.module.ActivityModule
import com.drivertest.donatenowapp.ui.dashboard.DashboardActivity
import com.drivertest.donatenowapp.ui.dashboard.DashboardActivityNavigation
import com.drivertest.donatenowapp.ui.login.LoginActivity

import dagger.Component

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

//    fun inject(activity: SplashActivity)
//
//    fun inject(activity: LoginActivity)
//
//    fun inject(activity: DummyActivity)

    fun inject(activity: LoginActivity)
    fun inject(activity: DashboardActivity)
    fun inject(activity: DashboardActivityNavigation)
}