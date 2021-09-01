package com.gmkornilov.shikimori.presentation.navigation.backstacks.appbackstacks

import com.github.terrakok.cicerone.Screen
import com.gmkornilov.shikimori.presentation.navigation.backstacks.Backstacks
import com.gmkornilov.shikimori.presentation.navigation.Screens
import com.gmkornilov.shikimori.presentation.navigation.backstacks.BackstackInfo
import kotlinx.parcelize.Parcelize

@Parcelize
class MainBackstackInfo : BackstackInfo {
    override fun getBackstackName(): String {
        return Backstacks.HOME.toString()
    }

    override fun getRootScreen(): Screen = Screens.HomeScreen()
}