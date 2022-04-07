package com.drivertest.donatenowapp.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.drivertest.donatenowapp.AppClass
import com.drivertest.donatenowapp.BuildConfig
import com.drivertest.donatenowapp.di.ApplicationContext
import com.drivertest.donatenowapp.di.TempDirectory
import com.drivertest.donatenowapp.remote.Networking
import com.drivertest.donatenowapp.utils.common.FileUtils
import com.drivertest.donatenowapp.utils.network.NetworkHelper
import com.drivertest.donatenowapp.utils.rx.SchedulerProvider
import com.drivertest.donatenowapp.remote.NetworkService
import com.mindorks.bootcamp.instagram.utils.rx.RxSchedulerProvider

import dagger.Module
import dagger.Provides

import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AppClass) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    @Singleton
    @TempDirectory
    fun provideTempDirectory() = FileUtils.getDirectory(application, "temp")


//    @Provides
//    @Singleton
//    @TempDirectory
//    fun provideTempDirectory() = FileUtils.getDirectory(application, "temp")

    /**
     * Since this function do not have @Singleton then each time CompositeDisposable is injected
     * then a new instance of CompositeDisposable will be provided
     */
    @Provides
    @Singleton
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
        application.getSharedPreferences("bootcamp-instagram-project-prefs", Context.MODE_PRIVATE)

    /**
     * We need to write @Singleton on the provide method if we are create the instance inside this method
     * to make it singleton. Even if we have written @Singleton on the instance's class
     */
//    @Provides
//    @Singleton
//    fun provideDatabaseService(): DatabaseService =
//        Room.databaseBuilder(
//            application, DatabaseService::class.java,
//            "bootcamp-instagram-project-db"
//        ).build()
//
    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            "",
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)
}