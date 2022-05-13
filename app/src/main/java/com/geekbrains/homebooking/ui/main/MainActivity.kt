package com.geekbrains.homebooking.ui.main

import com.geekbrains.homebooking.ui.base.BackButtonListener
import android.os.Bundle
import com.geekbrains.homebooking.App
import com.geekbrains.homebooking.R
import com.geekbrains.homebooking.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.bottomnavigation.BottomNavigationView
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    private var _binding: ActivityMainBinding? = null
    private val binding
        get() = _binding!!

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = AppNavigator(this, R.id.container)
    private val presenter by moxyPresenter {
        App.instance.appComponent.mainPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView
        navView.setOnItemSelectedListener{ menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_find -> {
                    presenter.findButtonPressed()
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_sign_in -> {
                    presenter.signinButtonPressed()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }


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
