package com.drivertest.donatenowapp.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.drivertest.donatenowapp.di.ViewModelProviderFactory
import com.drivertest.donatenowapp.repository.DashboardRepository
import com.drivertest.donatenowapp.repository.UserRepository

import com.drivertest.donatenowapp.ui.base.BaseActivity
import com.drivertest.donatenowapp.ui.dashboard.DashboardViewModel
import com.drivertest.donatenowapp.ui.login.LoginViewModel
import com.drivertest.donatenowapp.utils.network.NetworkHelper
import com.drivertest.donatenowapp.utils.rx.SchedulerProvider

import dagger.Module
import dagger.Provides

import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Kotlin Generics Reference: https://kotlinlang.org/docs/reference/generics.html
 * Basically it means that we can pass any class that extends BaseActivity which take
 * BaseViewModel subclass as parameter
 */
@Module
class ActivityModule(private val activity: BaseActivity<*,*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)
//
//    @Provides
//    fun provideSplashViewModel(
//        schedulerProvider: SchedulerProvider,
//        compositeDisposable: CompositeDisposable,
//        networkHelper: NetworkHelper,
//        userRepository: UserRepository
//    ): SplashViewModel = ViewModelProviders.of(
//        activity, ViewModelProviderFactory(SplashViewModel::class) {
//            SplashViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
//            //this lambda creates and return SplashViewModel
//        }).get(SplashViewModel::class.java)

//    @Provides
//    fun provideDummyViewModel(
//        schedulerProvider: SchedulerProvider,
//        compositeDisposable: CompositeDisposable,
//        networkHelper: NetworkHelper,
//        dummyRepository: DummyRepository
//    ): DummyViewModel = ViewModelProviders.of(
//        activity, ViewModelProviderFactory(DummyViewModel::class) {
//            DummyViewModel(schedulerProvider, compositeDisposable, networkHelper, dummyRepository)
//        }).get(DummyViewModel::class.java)
//
    @Provides
    fun provideLoginViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): LoginViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(LoginViewModel::class) {
            LoginViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
        }).get(LoginViewModel::class.java)


//    schedulerProvider: SchedulerProvider,
//    compositeDisposable: CompositeDisposable,
//    private val userRepository: UserRepository,
//
//    networkHelper: NetworkHelper,
//    private val dashboardRepository: DashboardRepository

    @Provides
    fun provideDashboardViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        userRepository: UserRepository,
        networkHelper: NetworkHelper,
        dashboardRepository: DashboardRepository

    ): DashboardViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(DashboardViewModel::class) {
            DashboardViewModel(schedulerProvider, compositeDisposable,userRepository,networkHelper,dashboardRepository)
        }).get(DashboardViewModel::class.java)


//    @Provides
//    fun provideMainViewModel(
//        schedulerProvider: SchedulerProvider,
//        compositeDisposable: CompositeDisposable,
//        networkHelper: NetworkHelper
//    ): MainViewModel = ViewModelProviders.of(
//        activity, ViewModelProviderFactory(MainViewModel::class) {
//            MainViewModel(schedulerProvider, compositeDisposable, networkHelper)
//        }).get(MainViewModel::class.java)

//    @Provides
//    fun provideMainSharedViewModel(
//        schedulerProvider: SchedulerProvider,
//        compositeDisposable: CompositeDisposable,
//        networkHelper: NetworkHelper
//    ): MainSharedViewModel = ViewModelProviders.of(
//        activity, ViewModelProviderFactory(MainSharedViewModel::class) {
//            MainSharedViewModel(schedulerProvider, compositeDisposable, networkHelper)
//        }).get(MainSharedViewModel::class.java)
}