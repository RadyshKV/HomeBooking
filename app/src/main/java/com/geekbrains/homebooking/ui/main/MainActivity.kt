package com.geekbrains.homebooking.ui.main

import com.geekbrains.homebooking.ui.base.BackButtonListener
import android.os.Bundle
import com.geekbrains.homebooking.App
import com.geekbrains.homebooking.R
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = AppNavigator(this, R.id.container)
    private val presenter by moxyPresenter {
        App.instance.appComponent.mainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
    }


    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        //super.onBackPressed()

        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {

                return
            }
        }
        presenter.backPressed()
    }
}
//import android.os.Bundle
//import com.google.android.material.bottomnavigation.BottomNavigationView
//import androidx.appcompat.app.AppCompatActivity
//import com.geekbrains.homebooking.databinding.ActivityMainBinding
//import com.geekbrains.homebooking.ui.cities.CitiesFragment
//import com.geekbrains.homebooking.ui.dashboard.DashboardFragment
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityMainBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        setTheme(R.style.Theme_HomeBooking)
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
////        if (savedInstanceState == null) {
////            supportFragmentManager.beginTransaction()
////                .replace(R.id.container, CitiesFragment.newInstance())
////                .commitNow()
////        }
//            val navView: BottomNavigationView = binding.navView
//        navView.setOnNavigationItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.navigation_find -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.container,
//                            CitiesFragment.newInstance())
//                        .commitAllowingStateLoss()
//                    true
//                }
//                R.id.navigation_sign_in -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.container,
//                            DashboardFragment()
//                        )
//                        .commitAllowingStateLoss()
//                    true
//                }
//                else -> false
//            }
//        }
//      navView.selectedItemId = R.id.navigation_find
//
////        val navController = findNavController(R.id.nav_host_fragment_activity_main)
////        // Passing each menu ID as a set of Ids because each
////        // menu should be considered as top level destinations.
////        val appBarConfiguration = AppBarConfiguration(
////            setOf(
////                R.id.navigation_find, R.id.navigation_sign_in
////            )
////        )
////        setupActionBarWithNavController(navController, appBarConfiguration)
////        navView.setupWithNavController(navController)
//    }
//}