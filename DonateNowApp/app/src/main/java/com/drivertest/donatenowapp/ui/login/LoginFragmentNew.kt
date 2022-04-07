package com.drivertest.donatenowapp.ui.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.drivertest.donatenowapp.databinding.FragmentLoginBinding

class LoginFragmentNew : Fragment(), View.OnClickListener {
    private var param1: String? = null
    private var param2: String? = null
   private var _binding: FragmentLoginBinding? = null
    private lateinit var callback : MyFragmentCallback
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }
    internal interface MyFragmentCallback {
        fun changeToStopDetails()
    }

    override fun onAttach(context: Context) {
        callback = context as MyFragmentCallback
        super.onAttach(context)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
      //  return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.loginButton?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
       callback.changeToStopDetails()

    }
}