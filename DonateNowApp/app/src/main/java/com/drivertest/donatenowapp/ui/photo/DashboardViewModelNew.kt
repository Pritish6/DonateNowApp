package com.drivertest.donatenowapp.ui.photo

import androidx.lifecycle.MutableLiveData
import com.drivertest.donatenowapp.repository.DashboardRepository
import com.drivertest.donatenowapp.repository.UserRepository
import com.drivertest.donatenowapp.ui.base.BaseViewModel
import com.drivertest.donatenowapp.utils.network.NetworkHelper
import com.drivertest.donatenowapp.utils.rx.SchedulerProvider

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.io.File
import java.io.InputStream

class DashboardViewModelNew(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val userRepository: UserRepository,

    networkHelper: NetworkHelper,
    private val directory: File
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

//    private val user: User = userRepository.getCurrentUser()!! // should not be used without logged in user
//
//    val loading: MutableLiveData<Boolean> = MutableLiveData()
//    val post: MutableLiveData<Event<Post>> = MutableLiveData()

    override fun onCreate() {}

//    fun onGalleryImageSelected(inputStream: InputStream) {
//        loading.postValue(true)
//        compositeDisposable.add(
//            Single.fromCallable {
//                FileUtils.saveInputStreamToFile(
//                    inputStream, directory, "gallery_img_temp", 500
//                )
//            }
//                .subscribeOn(schedulerProvider.io())
//                .subscribe(
//                    {
//                        if (it != null) {
//                            FileUtils.getImageSize(it)?.run {
//                                uploadPhotoAndCreatePost(it, this)
//                            }
//                        } else {
//                            loading.postValue(false)
//                            messageStringId.postValue(Resource.error(R.string.try_again))
//
//                        }
//                    },
//                    {
//                        loading.postValue(false)
//                        messageStringId.postValue(Resource.error(R.string.try_again))
//                    }
//                )
//        )
//    }
//
//    fun onCameraImageTaken(cameraImageProcessor: () -> String) {
//        loading.postValue(true)
//        compositeDisposable.add(
//            Single.fromCallable { cameraImageProcessor() }
//                .subscribeOn(schedulerProvider.io())
//                .subscribe(
//                    {
//                        File(it).apply {
//                            FileUtils.getImageSize(this)?.let { size ->
//                                uploadPhotoAndCreatePost(this, size)
//                            } ?: loading.postValue(false)
//                        }
//                    },
//                    {
//                        loading.postValue(false)
//                        messageStringId.postValue(Resource.error(R.string.try_again))
//                    }
//                )
//        )
//    }
//
//    private fun uploadPhotoAndCreatePost(imageFile: File, imageSize: Pair<Int, Int>) {
//        Logger.d("DEBUG", imageFile.path)
//        compositeDisposable.add(
//            photoRepository.uploadPhoto(imageFile, user)
//                .flatMap {
//                    postRepository.createPost(it, imageSize.first, imageSize.second, user)
//                }
//                .subscribeOn(schedulerProvider.io())
//                .subscribe(
//                    {
//                        loading.postValue(false)
//                        post.postValue(Event(it))
//                    },
//                    {
//                        handleNetworkError(it)
//                        loading.postValue(false)
//                    }
//                )
//
//        )
//    }
}