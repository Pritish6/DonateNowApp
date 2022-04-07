package com.drivertest.donatenowapp.ui.dashboard.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.drivertest.donatenowapp.databinding.FragmentDashboardBinding
import com.drivertest.donatenowapp.databinding.FragmentHomeBinding
import com.drivertest.donatenowapp.di.component.FragmentComponent
import com.drivertest.donatenowapp.ui.base.BaseFragment

import com.drivertest.donatenowapp.ui.dashboard.DashboardFragment
import com.drivertest.donatenowapp.ui.dashboard.DashboardViewModel
import com.drivertest.donatenowapp.ui.login.LoginViewModel


class HomeFragment : BaseFragment<LoginViewModel, FragmentHomeBinding>() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val activityViewModel: DashboardViewModel by activityViewModels()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_dashboard, container, false)
//    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment DashboardFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            DashboardFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }

//    override fun provideLayoutId(): Int {
//        TODO("Not yet implemented")
//    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {

        activityViewModel.getLoginData()
    }

    override fun getViewBinding(): FragmentHomeBinding {
        //FragmentUserListBinding.inflate(inflater, container, false)
        return  FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun setupObservers() {
        super.setupObservers()
        activityViewModel.loggingIn.observe(this, {

            binding.apply {
                binding.pbLoadingDashboard.visibility = if (it) View.VISIBLE else View.INVISIBLE}
        })
        activityViewModel.user.observe(this,{


            binding.apply {
                donoridtext.setText(it.id.toString())
                donoridbloodgroup.setText(it.bloodGrp)
            }

        })
    }
}