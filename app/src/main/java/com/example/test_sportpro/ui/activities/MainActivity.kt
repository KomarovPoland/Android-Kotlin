package com.example.test_sportpro.ui.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.test_sportpro.R
import com.example.test_sportpro.repository.SportRepository
import com.example.test_sportpro.ui.SportViewModel
import com.example.test_sportpro.ui.SportViewModelProviderFactory
import com.example.test_sportpro.ui.fragments.judgeProfile.JudgeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: SportViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val newsRepository = SportRepository()
        val viewModelProviderFactory = SportViewModelProviderFactory(application,newsRepository)

        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(SportViewModel::class.java)

        setUpNav()
    }

    private fun setUpNav() {
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration =  AppBarConfiguration
            .Builder(R.id.newsFragment, R.id.profileFragment, R.id.mainProfileFragment, R.id.judgeFragment)
            .build()

        NavigationUI.setupWithNavController(bottomNavigation, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.newsFragment -> showBottomNav()
                R.id.profileFragment -> showBottomNav()
                R.id.numberFragment -> showBottomNav()
                R.id.codeFragment -> showBottomNav()
                R.id.registerFragment -> showBottomNav()
                R.id.confirmationFragment -> showBottomNav()
                else -> hideBottomNav()
            }
            toolbar.title = navController.currentDestination?.label
        }
    }

    private fun showBottomNav() {
        bottomNavigation.visibility = View.VISIBLE

    }

    private fun hideBottomNav() {
        bottomNavigation.visibility = View.GONE

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return false
    }

}