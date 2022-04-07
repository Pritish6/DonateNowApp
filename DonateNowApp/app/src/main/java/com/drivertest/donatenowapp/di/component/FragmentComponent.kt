package com.drivertest.donatenowapp.di.component


import com.drivertest.donatenowapp.di.FragmentScope
import com.drivertest.donatenowapp.di.module.FragmentModule
import com.drivertest.donatenowapp.ui.base.BaseFragment
import com.drivertest.donatenowapp.ui.dashboard.DashboardFragment
import com.drivertest.donatenowapp.ui.dashboard.ui.home.HomeFragment
import com.drivertest.donatenowapp.ui.photo.PhotoFragment

import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {


//
   fun inject(fragment: DashboardFragment)
//
  fun inject(fragment: PhotoFragment)
//
   fun inject(fragment: HomeFragment)
}