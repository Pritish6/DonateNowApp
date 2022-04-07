package com.drivertest.donatenowapp.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.drivertest.donatenowapp.databinding.ActivityLoginBinding
import com.drivertest.donatenowapp.databinding.FragmentDashboardBinding
import com.drivertest.donatenowapp.di.component.FragmentComponent
import com.drivertest.donatenowapp.ui.base.BaseFragment
import com.drivertest.donatenowapp.ui.login.LoginViewModel
import com.drivertest.donatenowapp.ui.photo.DashboardViewModelNew

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : BaseFragment<LoginViewModel,FragmentDashboardBinding>() {
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

//    override fun provideLayoutId(): Int {
//        TODO("Not yet implemented")
//    }

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {

        activityViewModel.getLoginData()
    }

    override fun getViewBinding(): FragmentDashboardBinding {
        //FragmentUserListBinding.inflate(inflater, container, false)
        return  FragmentDashboardBinding.inflate(layoutInflater)
    }

    override fun setupObservers() {
        super.setupObservers()
        activityViewModel.loggingIn.observe(this, {

            binding.apply {
                binding.pbLoadingDashboard.visibility = if (it) View.VISIBLE else View.INVISIBLE}
        })
//        activityViewModel.enable_disable.observe(this, Observer {
//            activityViewModel.changedonorstatusNormal("Active")
//        })
        activityViewModel.user.observe(this,{


            binding.apply {
                donoridtext.setText(it.id.toString())
                donoridbloodgroup.setText(it.bloodGrp)
            }

        })
    }
}