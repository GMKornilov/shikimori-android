package com.gmkornilov.shikimori.presentation.mainactivity

import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.*
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.databinding.ActivityMainBinding
import com.gmkornilov.shikimori.di.app.GlobalNavigation
import com.gmkornilov.shikimori.presentation.ShikimoriApplication
import com.gmkornilov.shikimori.presentation.extensions.mapVisibility
import com.gmkornilov.shikimori.presentation.extensions.visibleFragment
import com.gmkornilov.shikimori.presentation.navigation.backstacks.Backstacks
import com.gmkornilov.shikimori.presentation.navigation.Screens
import com.gmkornilov.shikimori.presentation.navigation.backstacks.BackPressedListener
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    @Inject
    @GlobalNavigation
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    @GlobalNavigation
    lateinit var router: Router

    @Inject
    lateinit var connectivityManager: ConnectivityManager

    private val viewModel: MainActivityViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainActivityViewModel(connectivityManager) as T
            }
        }
    }

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    private val navigator = AppNavigator(this, R.id.mainContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ShikimoriApplication.INSTANCE.appComponent.inject(this)

        viewModel.connectionRestored.observe(this, {
            binding.internetRestoredText.visibility = View.VISIBLE
            binding.internetRestoredText.postDelayed({
                binding.internetRestoredText.visibility = View.GONE
            }, 3000)
        })

        viewModel.hasInternet.observe(this, {
            showNetworkConnectionMessage(it)
        })

        if (savedInstanceState == null) {
            viewModel.registerListener()
            /*
            setOnItemSelectedListener is not called for default menu item, so we
            get default menu item and trigger its "selection" ourselves
             */
            with(binding.bottomNavigation) {
                val item = menu.findItem(selectedItemId)
                itemSelected(item)
            }
        }

        binding.bottomNavigation.setOnItemSelectedListener {
            Log.d("navigation", "selected with $it")
            return@setOnItemSelectedListener itemSelected(it)

        }
        binding.bottomNavigation.setOnItemReselectedListener {
            Log.d("navigation", "reselected with $it")
            itemSelected(it)
        }

        supportActionBar?.hide()
    }

    override fun onStart() {
        super.onStart()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        val visibleFragment = supportFragmentManager.visibleFragment()
        if (visibleFragment != null && visibleFragment is BackPressedListener) {
            visibleFragment.onBackPressed()
        } else {
            router.exit()
        }
    }

    private fun showNetworkConnectionMessage(isVisible: Boolean) {
        binding.notInternetText.visibility = mapVisibility(!isVisible)
    }

    private fun itemSelected(menuItem: MenuItem): Boolean {
        val backstack =
            Backstacks.findByTabItemId(menuItem.itemId) ?: return false

        val tab = backstack.toString()

        val fm = supportFragmentManager
        val currentFragment = fm.visibleFragment()
        val newFragment = fm.findFragmentByTag(tab)
        if (currentFragment != null && newFragment != null && currentFragment === newFragment) {
            return false
        }
        val transaction = fm.beginTransaction()
        if (newFragment == null) {
            transaction.add(
                R.id.mainContainer,
                Screens.Backstack(backstack.backstackInfo).createFragment(fm.fragmentFactory),
                tab
            )
        }
        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }
        if (newFragment != null) {
            transaction.show(newFragment)
        }
        transaction.commitNow()

        return true
    }
}