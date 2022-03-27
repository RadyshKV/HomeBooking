package com.geekbrains.homebooking

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.homebooking.databinding.ActivityMainBinding
import com.geekbrains.homebooking.ui.cities.CitiesFragment
import com.geekbrains.homebooking.ui.cities.StartFragment
import com.geekbrains.homebooking.ui.dashboard.DashboardFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_HomeBooking)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, CitiesFragment.newInstance())
//                .commitNow()
//        }
            val navView: BottomNavigationView = binding.navView
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_find -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container,
                            CitiesFragment.newInstance())
                        .commitAllowingStateLoss()
                    true
                }
                R.id.navigation_sign_in -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container,
                            DashboardFragment()
                        )
                        .commitAllowingStateLoss()
                    true
                }
                else -> false
            }
        }
      navView.selectedItemId = R.id.navigation_find

//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_find, R.id.navigation_sign_in
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
    }
}