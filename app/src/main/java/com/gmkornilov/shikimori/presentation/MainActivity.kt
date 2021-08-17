package com.gmkornilov.shikimori.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import com.github.terrakok.cicerone.BackTo
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.presentation.navigation.Screens
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private val navigator = object : AppNavigator(this, R.id.mainContainer) {
        fun hasScreens(): Boolean {
            return fragmentManager.backStackEntryCount > 1
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            router.newRootScreen(Screens.MainScreen())
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)

        val menuItem = menu.findItem(R.id.search)
        val searchView = menuItem.actionView as SearchView

        searchView.queryHint = getString(R.string.search_anime)

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigator.hasScreens()
    }
}