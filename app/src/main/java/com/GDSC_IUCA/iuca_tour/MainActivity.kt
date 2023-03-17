package com.GDSC_IUCA.iuca_tour

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.GDSC_IUCA.iuca_tour.databinding.ActivityMainBinding
import com.GDSC_IUCA.iuca_tour.utils.NetworkChangeListener
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    var networkChangeListener = NetworkChangeListener()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val view = binding.root
//        setContentView(view)






        setupNav()
        setSupportActionBar(binding.myToolbar)


        val navController2 = findNavController(R.id.fragmentContainerView)

        binding.lngBtnTopBar.setOnClickListener {
            navController2.navigateUp() // to clear previous navigation history
            navController2.navigate(R.id.languageFragment)
        }


        // bottom navigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragmentContainerView)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.firstFragment,
                R.id.secondFragment,
                R.id.thirdFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNavigationView.setupWithNavController(navController)

        val config = AppBarConfiguration(navController.graph)
        findViewById<Toolbar>(R.id.my_toolbar).setupWithNavController(navController, config)


    }

    override fun onStart() {
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkChangeListener, intentFilter)
        super.onStart()
    }

    override fun onStop() {
        unregisterReceiver(networkChangeListener)
        super.onStop()
    }

    // hide the bottom nav bar
    private fun setupNav() {
        val navController = findNavController(R.id.fragmentContainerView)
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            .setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.firstFragment -> showBottomNav()
                R.id.secondFragment -> showBottomNav()
                R.id.thirdFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }


    private fun showBottomNav() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.bottomNavigationView.visibility = View.GONE
    }



}