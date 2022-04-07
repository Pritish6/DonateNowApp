package com.drivertest.donatenowapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.Observer
import com.drivertest.donatenowapp.Model.User
import com.drivertest.donatenowapp.R
import com.drivertest.donatenowapp.databinding.ActivityLoginBinding
import com.drivertest.donatenowapp.di.component.ActivityComponent
import com.drivertest.donatenowapp.ui.Hospital.HospitalDashboardActivity
import com.drivertest.donatenowapp.ui.base.BaseActivity
import com.drivertest.donatenowapp.ui.dashboard.DashboardActivityNavigation
import com.drivertest.donatenowapp.utils.common.Status


class LoginActivity : BaseActivity<LoginViewModel,ActivityLoginBinding>(), LoginFragmentNew.MyFragmentCallback,
    RadioGroup.OnCheckedChangeListener {
 //   private var _binding: ActivityLoginBinding? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//        if (savedInstanceState == null) {
//            supportFragmentManager.commit {
//                setReorderingAllowed(true)
//                add<LoginFragmentNew>(R.id.fragmentContainerView)
//            }
//        }
//    }

    override fun changeToStopDetails() {
        val intent = Intent(this, DashboardActivityNavigation::class.java)
        startActivity(intent)
//        supportFragmentManager.commit {
//            setReorderingAllowed(true)
//            replace<DashboardFragment>(R.id.fragmentContainerView).addToBackStack(null)
//        }

    }

//    override fun provideLayoutId(): Int {
//      return R.layout.activity_login
//    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }
//
//    override fun setupView(savedInstanceState: Bundle?) {
//        TODO("Not yet implemented")
//    }
//
    override fun setupView(savedInstanceState: Bundle?) {
   // binding.setVariable(viewModel)
 //  binding.mviewModel = viewModel
    val radioGroup = binding.radioGroup
    onRadioButtonClicked(radioGroup)
   val radioBtnDonor = binding.radioDonor
//    val radioBtnHosp = binding.radioPirates
    radioBtnDonor.isChecked = true
    //radioGroup.setOnCheckedChangeListener(this)
        binding.enteruserNameEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onEmailChange(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onPasswordChange(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        binding.loginButton.setOnClickListener {
            var radioButtonID: Int = radioGroup.checkedRadioButtonId;
            if(radioButtonID==R.id.radio_donor){
            viewModel.onLogin() }
        else{
                startActivity(Intent(applicationContext, HospitalDashboardActivity::class.java))
                finish()
        }

        }
    }
//
    override fun setupObservers() {
        super.setupObservers()
        // Event is used by the view model to tell the activity to launch another activity
        // view model also provided the Bundle in the event that is needed for the Activity
        viewModel.launchMain.observe(this, Observer<User> {
//            it.getIfNotHandled()?.run {
//                startActivity(Intent(applicationContext, DashboardActivity::class.java))
//                finish()
//            }



            it.run {
                            startActivity(Intent(applicationContext, DashboardActivityNavigation::class.java))
                finish() }
        })

        viewModel.emailField.observe(this, Observer {
            if (binding.enteruserNameEditText?.text.toString() != it) binding?.enteruserNameEditText?.setText(it)
        })

        viewModel.emailValidation.observe(this, Observer {
            when (it.status) {
                Status.ERROR -> binding?.enterUserNameLayout?.error = it.data?.run { getString(this) }
                else -> binding?.enterUserNameLayout?.isErrorEnabled = false
            }
        })

        viewModel.passwordField.observe(this, Observer {
            if (binding?.passwordEditText?.text.toString() != it) binding?.enteruserNameEditText?.setText(it)
        })

        viewModel.passwordValidation.observe(this, Observer {
            when (it.status) {
                Status.ERROR -> binding?.enterPasswordLayout?.error = it.data?.run { getString(this) }
                else -> binding.enterPasswordLayout?.isErrorEnabled = false
            }
        })

        viewModel.loggingIn.observe(this, Observer {

            binding.apply {
           binding.pbLoading.visibility = if (it) View.VISIBLE else View.GONE}
//            val act =  LoginActivity
//            act.runOnUiThread {
//                _binding?.root?.post {
//                    _binding?.pbLoading?.visibility = if (it) View.VISIBLE else View.GONE
//                }
//            }

        })
    }

    override fun getViewBinding(): ActivityLoginBinding {
        return  ActivityLoginBinding.inflate(layoutInflater)

    }

//    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
//        when(p1){
//           R.id.radio_donor -> { Toast.makeText(this,"Donor",Toast.LENGTH_LONG).show(); binding.radioPirates.isChecked=false }
//            R.id.radio_hospital -> { Toast.makeText(this,"Hospital",Toast.LENGTH_LONG).show(); binding.radioDonor.isChecked=false }
//        }
//    }


    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio_donor ->
                    if (checked) {
                        // Pirates are the best
                        binding.DonateNowText.setText("I AM A DONOR")
                       // Toast.makeText(this,"Donor",Toast.LENGTH_LONG).show();
                    }
                R.id.radio_hospital ->
                    if (checked) {
                        // Ninjas rule
                        binding.DonateNowText.setText("I AM A HOSPITAL")
                    }
            }
        }
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        binding.unbind()
        super.onDestroy()

    }
}