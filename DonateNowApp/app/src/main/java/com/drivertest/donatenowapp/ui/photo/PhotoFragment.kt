package com.drivertest.donatenowapp.ui.photo

import android.os.Bundle
import android.view.View
import com.drivertest.donatenowapp.databinding.FragmentDashboardBinding
import com.drivertest.donatenowapp.di.component.FragmentComponent
import com.drivertest.donatenowapp.ui.base.BaseFragment
import com.drivertest.donatenowapp.ui.dashboard.DashboardViewModel


class PhotoFragment : BaseFragment<DashboardViewModel,FragmentDashboardBinding>() {

    companion object {

        const val TAG = "PhotoFragment"
        const val RESULT_GALLERY_IMG = 1001

        fun newInstance(): PhotoFragment {
            val args = Bundle()
            val fragment = PhotoFragment()
            fragment.arguments = args
            return fragment
        }
    }

//    @Inject
//    lateinit var mainSharedViewModel: MainSharedViewModel
//
//    @Inject
//    lateinit var camera: Camera



    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

//        viewModel.loading.observe(this, Observer {
//            pb_loading.visibility = if (it) View.VISIBLE else View.GONE
//        })
//
//        viewModel.post.observe(this, Observer {
//            it.getIfNotHandled()?.run {
//                mainSharedViewModel.newPost.postValue(Event(this))
//                mainSharedViewModel.onHomeRedirect()
//            }
//        })
    }

    override fun setupView(view: View) {
        TODO("Not yet implemented")
    }

    override fun getViewBinding(): FragmentDashboardBinding {
        return  FragmentDashboardBinding.inflate(layoutInflater)
    }

//    override fun setupView(view: View) {
//        view_gallery.setOnClickListener {
//            Intent(Intent.ACTION_PICK)
//                .apply {
//                    type = "image/*"
//                }.run {
//                    startActivityForResult(this, RESULT_GALLERY_IMG)
//                }
//        }
//
//        view_camera.setOnClickListener {
//            try {
//                camera.takePicture()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//        }
//    }
//
//    override fun onActivityResult(reqCode: Int, resultCode: Int, intent: Intent?) {
//        super.onActivityResult(reqCode, resultCode, intent)
//        if (resultCode == RESULT_OK) {
//            when (reqCode) {
//                RESULT_GALLERY_IMG -> {
//                    try {
//                        intent?.data?.let {
//                            activity?.contentResolver?.openInputStream(it)?.run {
//                                viewModel.onGalleryImageSelected(this)
//                            }
//                        } ?: showMessage(R.string.try_again)
//                    } catch (e: FileNotFoundException) {
//                        e.printStackTrace()
//                        showMessage(R.string.try_again)
//                    }
//                }
//                Camera.REQUEST_TAKE_PHOTO -> {
//                    viewModel.onCameraImageTaken { camera.cameraBitmapPath }
//                }
//            }
//        }
//    }

}