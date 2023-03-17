package com.GDSC_IUCA.iuca_tour.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.GDSC_IUCA.iuca_tour.MainActivity
import com.GDSC_IUCA.iuca_tour.R
import com.GDSC_IUCA.iuca_tour.ViewModel.ActivityViewModel
import com.GDSC_IUCA.iuca_tour.databinding.ActivityMainPageBinding
import com.google.android.material.navigation.NavigationView


class MainPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainPageBinding
    private lateinit var listOfPlaceNames: ArrayList<String>

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPageBinding.inflate(layoutInflater)

        binding.btnExit.setOnClickListener {
            var dialog = CustomDialogFragment()
            dialog.show(supportFragmentManager, "customDialog")
        }


        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_content_main2)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.mainPageFragment, R.id.mainPageAltFragment),
            drawerLayout
        )


        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)


        listOfPlaceNames = ArrayList()

        // Shared preference
        val sharedPre = this.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val counter = sharedPre?.getInt("counter", 0)
        val setOrderedPlaces = sharedPre?.getString("setOrderedPlaces", null)
        val listOrderedPlaces: List<Char> = setOrderedPlaces!!.toList()
        val idOfCurrentPlace = listOrderedPlaces.elementAt(counter!!.toInt())

        Log.d("ID OF CUR PLACE", idOfCurrentPlace.toString())
        Log.d("LIST OF PLACE", listOrderedPlaces.toString())

        val viewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)
        viewModel.getPost1()
    }

    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.nav_host_fragment_content_main2)

        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Вы уверены, что хотите завершить тур")

        builder.setPositiveButton("Да") { dialog, which ->
            Toast.makeText(applicationContext,
                "Тур завершон", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }

        builder.setNegativeButton("Нет") { dialog, which ->
            Toast.makeText(applicationContext,
                "Продолжить", Toast.LENGTH_SHORT).show()
        }


        builder.show()


//        val count = supportFragmentManager.backStackEntryCount
//        if (count == 0) {
//            super.onBackPressed()
//
//            val sharedPre = this.getSharedPreferences("pref", Context.MODE_PRIVATE)
//            val editor = sharedPre?.edit()
//            var counter = sharedPre?.getInt("counter", 0)
//
//            Log.d("Counter", counter.toString())
//
//            if (counter == 2){
//                counter = counter.toInt() - 1
//
//            }
//
//
//            Log.d("Counter2", counter.toString())
//
//            editor?.apply {
//                putInt("counter", counter!!)
//            }?.apply()
//
//            Log.e("Activity", count.toString())
//            Log.e("Activity_Counter", counter.toString())
//
//            //additional code
//        } else {
//            supportFragmentManager.popBackStack()
//            Log.e("Activity_Counter", "else")
//
//        }
    }

}