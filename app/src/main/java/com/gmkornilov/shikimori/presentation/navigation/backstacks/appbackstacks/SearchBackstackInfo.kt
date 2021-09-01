package com.gmkornilov.shikimori.presentation.navigation.backstacks.appbackstacks

import com.github.terrakok.cicerone.Screen
import com.gmkornilov.shikimori.presentation.navigation.Backstacks
import com.gmkornilov.shikimori.presentation.navigation.Screens
import com.gmkornilov.shikimori.presentation.navigation.backstacks.BackstackInfo
import kotlinx.parcelize.Parcelize

@Parcelize
class SearchBackstackInfo : BackstackInfo {
    override fun getBackstackName(): String {
        return Backstacks.SEARCH.toString()
    }

    override fun getRootScreen(): Screen = Screens.Search()
}