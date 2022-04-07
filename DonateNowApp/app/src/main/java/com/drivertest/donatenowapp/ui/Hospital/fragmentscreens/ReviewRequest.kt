package com.drivertest.donatenowapp.ui.Hospital.fragmentscreens

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.drivertest.donatenowapp.R

class ReviewRequest : Fragment() {

    companion object {
        fun newInstance() = ReviewRequest()
    }

    private lateinit var viewModel: ReviewRequestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.review_request_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReviewRequestViewModel::class.java)
        // TODO: Use the ViewModel
    }

}