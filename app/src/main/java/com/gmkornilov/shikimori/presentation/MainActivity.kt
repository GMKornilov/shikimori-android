package com.gmkornilov.shikimori.presentation

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.github.terrakok.cicerone.Back
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.di.app.GlobalNavigation
import com.gmkornilov.shikimori.presentation.navigation.Screens
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    @GlobalNavigation
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    @GlobalNavigation
    lateinit var router: Router

    private val navigator = object : AppNavigator(this, R.id.mainContainer) {
        fun hasScreens(): Boolean {
            return fragmentManager.backStackEntryCount > 1
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ShikimoriApplication.instance.appComponent.inject(this)

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

    override fun onBackPressed() {
        navigator.applyCommands(arrayOf(Back()))
    }
}