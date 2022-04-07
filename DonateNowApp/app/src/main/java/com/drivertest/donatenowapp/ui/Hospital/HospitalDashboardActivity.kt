package com.drivertest.donatenowapp.ui.Hospital

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.drivertest.donatenowapp.R
import com.drivertest.donatenowapp.databinding.ActivityDashboardNavigationBinding
import com.drivertest.donatenowapp.databinding.ActivityTestBinding
import com.drivertest.donatenowapp.databinding.HospitalActivityDashboardNavigationBinding

import com.drivertest.donatenowapp.ui.Hospital.fragmentscreens.MapsFragment


import com.google.android.gms.maps.MapFragment
import com.google.android.material.navigation.NavigationView


class HospitalDashboardActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: HospitalActivityDashboardNavigationBinding
    private lateinit var drawerLayout : DrawerLayout
    private lateinit var navController:NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = HospitalActivityDashboardNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: NavigationView = binding.navViewHospital
       setSupportActionBar(binding.appBarHospitalDashboardActivityNavigation.toolbarHospital)
        drawerLayout = binding.drawerLayout
  //      val navController = findNavController(R.id.nav_host_fragment_content_hospital_activity_navigation)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_hospital_activity_navigation) as NavHostFragment
        navController = navHostFragment.navController


 //       val navHostFragment =
 //           supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_hospital_activity_navigation) as NavHostFragment
       // val navController = navHostFragment.navController
       // appBarConfiguration = AppBarConfiguration(navController.graph)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.search_Donor
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
//        appBarConfiguration = AppBarConfiguration(navController.graph)






//this.findNavController().navigate()

  //     navController.navigate(MapsFragmentDirections.actionMapsFragmentToSearchDonor())
//        var fragment: MapsFragment? = MapsFragment()
//        val transaction = supportFragmentManager.beginTransaction()
//        if (fragment != null) {
//            transaction.add(R.id.nav_host_fragment_content_hospital_activity_navigation, fragment).addToBackStack(null)
//        }
//        transaction.commit()

//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }
fun goToMaps(){
    val navController = findNavController(R.id.nav_host_fragment_content_hospital_activity_navigation)
    val bundle = bundleOf("amount" to String())
    navController.navigate(R.id.mapsFragment,bundle)
}
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_hospital_activity_navigation)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

//
//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_hospital_activity_navigation)
//        return navController.navigateUp()
//    }
}