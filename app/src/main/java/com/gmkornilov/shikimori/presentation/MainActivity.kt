package com.gmkornilov.shikimori.presentation

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.*
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.databinding.ActivityMainBinding
import com.gmkornilov.shikimori.di.app.GlobalNavigation
import com.gmkornilov.shikimori.presentation.navigation.Backstacks
import com.gmkornilov.shikimori.presentation.navigation.Screens
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    @Inject
    @GlobalNavigation
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    @GlobalNavigation
    lateinit var router: Router

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    /**
     * Property for getting key of current backstack
     */
    private val screenKey: String?
        get() {
            val currentItemId = binding.bottomNavigation.selectedItemId
            val currentKey = Backstacks.findByTabItemId(currentItemId)?.toString() ?: return null
            return currentKey
        }

    private val navigator = object : AppNavigator(this, R.id.mainContainer) {
        fun hasScreens(): Boolean {
            return fragmentManager.backStackEntryCount > 1
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ShikimoriApplication.instance.appComponent.inject(this)

        if (savedInstanceState == null) {
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

        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
            setLogo(R.drawable.ic_title)
            setDisplayUseLogoEnabled(true)
        }
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
        navigator.applyCommands(arrayOf(Back()))
    }

    private fun itemSelected(menuItem: MenuItem): Boolean {
        val backstack =
            Backstacks.findByTabItemId(menuItem.itemId) ?: return false

        val tab = backstack.toString()

        val fm = supportFragmentManager
        var currentFragment: Fragment? = null
        val fragments = fm.fragments
        for (f in fragments) {
            if (f.isVisible) {
                currentFragment = f
                break
            }
        }
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