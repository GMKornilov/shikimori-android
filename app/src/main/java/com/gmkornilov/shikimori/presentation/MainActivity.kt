package com.gmkornilov.shikimori.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.terrakok.cicerone.BackTo
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.gmkornilov.shikimori.R
import com.gmkornilov.shikimori.presentation.navigation.Screens
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = object : AppNavigator(this, R.id.mainContainer) {
        fun hasScreens(): Boolean {
            return fragmentManager.backStackEntryCount > 1
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf(BackTo(null), Replace(Screens.MainScreen())))
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

    override fun onSupportNavigateUp(): Boolean {
        return navigator.hasScreens()
    }
}