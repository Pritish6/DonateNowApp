package com.drivertest.donatenowapp.ui.dashboard

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.drivertest.donatenowapp.R
import com.drivertest.donatenowapp.R.*
import com.drivertest.donatenowapp.databinding.ActivityDashboardBinding
import com.drivertest.donatenowapp.di.component.ActivityComponent
import com.drivertest.donatenowapp.ui.base.BaseActivity


class DashboardActivity : BaseActivity<DashboardViewModel,ActivityDashboardBinding>() {
      lateinit var  drawerToggle:ActionBarDrawerToggle
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(layout.activity_dashboard)
////        setSupportActionBar(findViewById(R.id.toolbar))
////        val drawer = findViewById<DrawerLayout>(id.drawer)
////     //   setSupportActionBar(findViewById(toolbar))
////
////        drawerToggle = ActionBarDrawerToggle(this, drawer, string.open, string.close)
////        drawer.addDrawerListener(drawerToggle)
////        drawerToggle.syncState()
////
////
////
////       supportActionBar?.setDisplayHomeAsUpEnabled(true)
////        if (savedInstanceState == null) {
////            Toast.makeText(this,"In dashboard",Toast.LENGTH_LONG).show();
////            supportFragmentManager.commit {
////           //     setReorderingAllowed(true)
////                add<DashboardFragment>(id.dashboardFragmentContainerView)
////            }
////        }
//
//
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    override fun getViewBinding(): ActivityDashboardBinding {
        return  ActivityDashboardBinding.inflate(layoutInflater)
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        setSupportActionBar(findViewById(R.id.toolbar))
        val drawer = findViewById<DrawerLayout>(id.drawer)
        //   setSupportActionBar(findViewById(toolbar))

        drawerToggle = ActionBarDrawerToggle(this, drawer, string.open, string.close)
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()



        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        if (savedInstanceState == null) {
//            Toast.makeText(this,"In dashboard",Toast.LENGTH_LONG).show();
//            supportFragmentManager.commit {
//                //     setReorderingAllowed(true)
//                add<DashboardFragment>(id.dashboardFragmentContainerView)
//            }
//        }

    }
}