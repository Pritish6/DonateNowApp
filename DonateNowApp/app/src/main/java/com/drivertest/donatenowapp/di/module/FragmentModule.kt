package com.drivertest.donatenowapp.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.drivertest.donatenowapp.di.FragmentScope
import com.drivertest.donatenowapp.di.TempDirectory
import com.drivertest.donatenowapp.di.ViewModelProviderFactory
import com.drivertest.donatenowapp.repository.DashboardRepository
import com.drivertest.donatenowapp.repository.UserRepository
import com.drivertest.donatenowapp.ui.base.BaseFragment
import com.drivertest.donatenowapp.ui.dashboard.DashboardViewModel
import com.drivertest.donatenowapp.ui.login.LoginViewModel
import com.drivertest.donatenowapp.utils.network.NetworkHelper
import com.drivertest.donatenowapp.utils.rx.SchedulerProvider
import com.drivertest.donatenowapp.ui.photo.DashboardViewModelNew


import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import java.io.File

@Module
class FragmentModule(private val fragment: BaseFragment<*,*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)
    @Provides
    fun provideLoginViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): LoginViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(LoginViewModel::class) {
            com.drivertest.donatenowapp.ui.login.LoginViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper,
                userRepository
            )
        }).get(LoginViewModel::class.java)
//    @Provides
//    fun provideDashboardViewModel(schedulerProvider:SchedulerProvider,compositeDisposable:CompositeDisposable,networkHelper:NetworkHelper,userReository:UserRepository,dashboardRepository: DashboardRepository):DashboardViewModel=
//        ViewModelProviders.of(fragment,ViewModelProviderFactory(DashboardViewModel::class)
//        { DashboardViewModel(schedulerProvider, compositeDisposable, userReository,networkHelper,dashboardRepository)}).get(DashboardViewModel::class.java)

//    @Provides
//    fun provideDashboardViewModelNew(
//        schedulerProvider: SchedulerProvider,
//        compositeDisposable: CompositeDisposable,
//        userRepository: UserRepository,
//
//        networkHelper: NetworkHelper,
//        @TempDirectory directory: File
//    ): DashboardViewModelNew = ViewModelProviders.of(
//        fragment, ViewModelProviderFactory(DashboardViewModelNew::class) {
//            DashboardViewModelNew(
//                schedulerProvider, compositeDisposable, userRepository,
//                networkHelper, directory
//            )
//        }).get(DashboardViewModelNew::class.java)

//    @Provides
//    fun providePhotoViewModel(
//        schedulerProvider: SchedulerProvider,
//        compositeDisposable: CompositeDisposable,
//        userRepository: UserRepository,
//
//        networkHelper: NetworkHelper,
//        @TempDirectory directory: File
//    ): DashboardViewModelNew = ViewModelProviders.of(
//        fragment, ViewModelProviderFactory(DashboardViewModelNew::class) {
//            DashboardViewModelNew(
//                schedulerProvider, compositeDisposable, userRepository,
//                networkHelper, directory
//            )
//        }).get(DashboardViewModelNew::class.java)



//    @Provides
//    fun provideProfileViewModel(
//        schedulerProvider: SchedulerProvider,
//        compositeDisposable: CompositeDisposable,
//        networkHelper: NetworkHelper
//    ): ProfileViewModel = ViewModelProviders.of(
//        fragment,
//        com.mindorks.bootcamp.instagram.utils.ViewModelProviderFactory(ProfileViewModel::class) {
//            ProfileViewModel(schedulerProvider, compositeDisposable, networkHelper)
//        }).get(ProfileViewModel::class.java)

    @Provides
    fun provideDashboardViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        userReository: UserRepository,
        networkHelper: NetworkHelper,
        dashboardRepository: DashboardRepository
    ): DashboardViewModel =
        ViewModelProviders.of(fragment,
            ViewModelProviderFactory(DashboardViewModel::class) {
                DashboardViewModel(schedulerProvider, compositeDisposable,userReository, networkHelper, dashboardRepository)
            }
        ).get(DashboardViewModel::class.java)
//
//    @Provides
//    fun provideDummiesAdapter() = DummiesAdapter(fragment.lifecycle, ArrayList())
//
//    @Provides
//    fun providePostsAdapter() = PostsAdapter(fragment.lifecycle, ArrayList())
//
//    @Provides
//    fun provideCamera() = Camera.Builder()
//        .resetToCorrectOrientation(true)// it will rotate the camera bitmap to the correct orientation from meta data
//        .setTakePhotoRequestCode(1)
//        .setDirectory("temp")
//        .setName("camera_temp_img")
//        .setImageFormat(Camera.IMAGE_JPEG)
//        .setCompression(75)
//        .setImageHeight(500)// it will try to achieve this height as close as possible maintaining the aspect ratio;
//        .build(fragment)
//
//    @Provides
//    fun provideHomeViewModel(
//        schedulerProvider: SchedulerProvider,
//        compositeDisposable: CompositeDisposable,
//        networkHelper: NetworkHelper,
//        userRepository: UserRepository,
//        postRepository: PostRepository
//    ): HomeViewModel = ViewModelProviders.of(
//        fragment, ViewModelProviderFactory(HomeViewModel::class) {
//            HomeViewModel(
//                schedulerProvider, compositeDisposable, networkHelper, userRepository,
//                postRepository, ArrayList(), PublishProcessor.create()
//            )
//        }).get(HomeViewModel::class.java)
//
//    @Provides
//    fun provideProfileViewModel(
//        schedulerProvider: SchedulerProvider,
//        compositeDisposable: CompositeDisposable,
//        networkHelper: NetworkHelper
//    ): ProfileViewModel = ViewModelProviders.of(
//        fragment, ViewModelProviderFactory(ProfileViewModel::class) {
//            ProfileViewModel(schedulerProvider, compositeDisposable, networkHelper)
//        }).get(ProfileViewModel::class.java)
//

//
//    @Provides
//    fun provideMainSharedViewModel(
//        schedulerProvider: SchedulerProvider,
//        compositeDisposable: CompositeDisposable,
//        networkHelper: NetworkHelper
//    ): MainSharedViewModel = ViewModelProviders.of(
//        fragment.activity!!, ViewModelProviderFactory(MainSharedViewModel::class) {
//            MainSharedViewModel(schedulerProvider, compositeDisposable, networkHelper)
//        }).get(MainSharedViewModel::class.java)
}