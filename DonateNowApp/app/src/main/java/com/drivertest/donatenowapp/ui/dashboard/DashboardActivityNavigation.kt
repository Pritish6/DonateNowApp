package com.drivertest.donatenowapp.ui.dashboard

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.drivertest.donatenowapp.R
import com.drivertest.donatenowapp.databinding.ActivityDashboardBinding
import com.drivertest.donatenowapp.databinding.ActivityDashboardNavigationBinding
import com.drivertest.donatenowapp.di.component.ActivityComponent
import com.drivertest.donatenowapp.ui.base.BaseActivity
import java.lang.Exception


class DashboardActivityNavigation : BaseActivity<DashboardViewModel, ActivityDashboardNavigationBinding>()
{
    lateinit var  drawerToggle:ActionBarDrawerToggle
    private lateinit var appBarConfiguration: AppBarConfiguration
 //   private lateinit var binding: ActivityDashboardNavigationBinding
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
private lateinit var drawerLayout : DrawerLayout
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityDashboardNavigationBinding.inflate(layoutInflater)
//        setContentView(binding.root)
////        toolbar = findViewById(R.id.toolbar);
////        setSupportActionBar(toolbar);
////        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        setSupportActionBar(binding.appBarDashboardActivityNavigation.toolbar)
//
//        binding.appBarDashboardActivityNavigation.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
//        drawerLayout = binding.drawerLayout
//        val navView: NavigationView = binding.navView
//
//        val navController =
//            findNavController(R.id.nav_host_fragment_content_dashboard_activity_navigation)
//        val signoutMenuItem = navView.menu.findItem(R.id.showDialog)
//        signoutMenuItem.setOnMenuItemClickListener {
//           Toast.makeText(this,"clicked",Toast.LENGTH_LONG).show()
//            true
//        }
//      //  navController.addOnDestinationChangedListener(this)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.nav_home
//            ), drawerLayout
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
//
////        supportActionBar?.setDisplayHomeAsUpEnabled(true)
////        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
////        drawerLayout.addDrawerListener(drawerToggle)
////        drawerToggle.syncState()
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.dashboard_activity_navigation, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController =
            findNavController(R.id.nav_host_fragment_content_dashboard_activity_navigation)
     //   return navController.navigateUp(drawerLayout)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun getViewBinding(): ActivityDashboardNavigationBinding {
        return  ActivityDashboardNavigationBinding.inflate(layoutInflater)
    }

    override fun onResume() {
        super.onResume()
        Log.d("++++++","onresume")
    }
    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    inner class FireMissilesDialogFragment(checked: Boolean) : DialogFragment() {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return activity?.let {
                // Use the Builder class for convenient dialog construction
                val builder = AlertDialog.Builder(it)
                builder.setMessage("test")
                    .setPositiveButton("okay",
                        DialogInterface.OnClickListener { dialog, id ->
                            // FIRE ZE MISSILES!
                            dialog.cancel()
                        })
                    .setNegativeButton("cancel",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User cancelled the dialog
                            dialog.cancel()
                        })
                // Create the AlertDialog object and return it
                builder.create()
            } ?: throw IllegalStateException("Activity cannot be null")
        }
    }
fun dialogFragment(checked:Boolean){



}
fun showDialog(checked:Boolean): AlertDialog {
    val builder = AlertDialog.Builder(this)
    builder.setTitle("Alert!!!")
    if(checked)
       builder.setMessage("Feels like you are back. Activate your profile for this noble cause!!!")
    else
        builder.setMessage("Unavailable or not feeling like donating blood ? Deactivate your profile temporarily")
    builder.setPositiveButton("Accept", object: DialogInterface.OnClickListener {
        override fun onClick(dialog:DialogInterface, which:Int) {
            val status = when(checked) {
                true -> "Active"
                false -> "inactive"
            }
            try {
                viewModel.changedonorstatus(status)
            }catch (e:Exception){
                e.printStackTrace()
            }
            dialog.dismiss();

        }
    })
    builder.setNegativeButton("Cancel", object: DialogInterface.OnClickListener {
        override fun onClick(dialog:DialogInterface, which:Int) {
            dialog.dismiss()
        }
    })
    return builder.create()
    // Set other dialog properties

}
    override fun setupView(savedInstanceState: Bundle?) {

        setSupportActionBar(findViewById(R.id.toolbar))
        val drawer = findViewById<DrawerLayout>(R.id.drawer)
        //   setSupportActionBar(findViewById(toolbar))
//
//        drawerToggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
//        drawer.addDrawerListener(drawerToggle)
//        drawerToggle.syncState()
//
//
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        if (savedInstanceState == null) {
//            Toast.makeText(this,"In dashboard",Toast.LENGTH_LONG).show();
//            supportFragmentManager.commit {
//                //     setReorderingAllowed(true)
//                add<DashboardFragment>(R.id.dashboardFragmentContainerView)
//            }
//        }


      //          binding = ActivityDashboardNavigationBinding.inflate(layoutInflater)
     //   setContentView(binding.root)
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setSupportActionBar(binding.appBarDashboardActivityNavigation.toolbar)

//        binding.appBarDashboardActivityNavigation.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = binding.navView

        val navController =
            findNavController(R.id.nav_host_fragment_content_dashboard_activity_navigation)
        val signoutMenuItem = navView.menu.findItem(R.id.showenabledisable)

        val switch_id = signoutMenuItem.actionView as SwitchCompat
      //  switch_id.setChecked(true)
        switch_id.setOnClickListener(View.OnClickListener {
           drawerLayout.closeDrawer(Gravity.LEFT)
  //


            val alertDialog: AlertDialog = showDialog(switch_id.isChecked())
            // Set other dialog properties
            alertDialog.setCancelable(true)
            alertDialog.show()
//            Toast.makeText(
//                applicationContext,
//                if (switch_id.isChecked()) "is checked!!!" else "not checked!!!",
//                Toast.LENGTH_SHORT
//            ).show()
     //       viewModel.changedonorstatusNormal("Active")
//          val fragobj =  DashboardActivityNavigation().FireMissilesDialogFragment(true)
//fragobj.show(supportFragmentManager,"test");


        }
 //       val dialogFragment: DialogFragment = dialogFragment(switch_id.isChecked)

        )


//        signoutMenuItem.setOnMenuItemClickListener {
//           Toast.makeText(this,"clicked",Toast.LENGTH_LONG).show()
//            true
//        }
      //  navController.addOnDestinationChangedListener(this)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        drawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
//        drawerLayout.addDrawerListener(drawerToggle)
//        drawerToggle.syncState()

  //      viewModel.changedonorstatusNormal("Active")

    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.enable_disable.observe(this, Observer {
            viewModel.changedonorstatusNormal("Active")
        })
//        viewModel.loggingIn.observe(this, Observer {
//
//            binding.apply {
//                binding.pbLoadingDashboard.visibility = if (it) View.VISIBLE else View.INVISIBLE}
//        })
    }

}