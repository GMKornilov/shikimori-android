package com.gmkornilov.shikimori.presentation.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.gmkornilov.shikimori.presentation.mainpage.MainPageFragment

object Screens {
    fun MainScreen() = FragmentScreen {
        MainPageFragment.newInstance()
    }
}