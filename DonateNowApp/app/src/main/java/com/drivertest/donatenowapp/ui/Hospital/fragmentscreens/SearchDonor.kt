package com.drivertest.donatenowapp.ui.Hospital.fragmentscreens

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.drivertest.donatenowapp.R
import com.drivertest.donatenowapp.ui.Hospital.HospitalDashboardActivity

class SearchDonor : Fragment() {

    companion object {
        fun newInstance() = SearchDonor()
    }

    private lateinit var viewModel: SearchDonorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_donor_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchDonorViewModel::class.java)

        // TODO: Use the ViewModel

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      val btn =   view.findViewById<Button>(R.id.searchdonorsbtn)
        btn.setOnClickListener { (activity as HospitalDashboardActivity?)!!.goToMaps() }
    }
}