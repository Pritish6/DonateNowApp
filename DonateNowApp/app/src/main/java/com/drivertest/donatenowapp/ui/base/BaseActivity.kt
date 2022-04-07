package com.drivertest.donatenowapp.ui.base

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.drivertest.donatenowapp.AppClass
import com.drivertest.donatenowapp.databinding.ActivityDashboardNavigationBinding
import com.drivertest.donatenowapp.di.component.ActivityComponent
import com.drivertest.donatenowapp.di.component.DaggerActivityComponent


import com.drivertest.donatenowapp.di.module.ActivityModule
import com.drivertest.donatenowapp.utils.display.Toaster

import javax.inject.Inject

/**
 * Reference for generics: https://kotlinlang.org/docs/reference/generics.html
 * Basically BaseActivity will take any class that extends BaseViewModel
 */
abstract class BaseActivity<VM : BaseViewModel,B : ViewBinding> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM


    lateinit var binding: B
    override fun onCreate(savedInstanceState: Bundle?) {
      injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        setupObservers()
        setupView(savedInstanceState)
        viewModel.onCreate()

    }



    abstract fun getViewBinding(): B
    private fun buildActivityComponent() =
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as AppClass).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()

    protected open fun setupObservers() {
        viewModel.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })
    }

    private fun showMessage(message: String) = Toaster.show(applicationContext, message)

    private fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    open fun goBack() = onBackPressed()

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStackImmediate()
        else super.onBackPressed()
    }

//    @LayoutRes
//    protected abstract fun provideLayoutId(): Int

   protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    protected abstract fun setupView(savedInstanceState: Bundle?)

    override fun onDestroy() {

        super.onDestroy()
    }
}