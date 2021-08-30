package com.gmkornilov.shikimori.presentation.navigation

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.gmkornilov.shikimori.R

enum class Backstacks(
    @IdRes val tabItemId: Int,
    @StringRes val tabNameRes: Int,
    val tabScreen: FragmentScreen,
) {
    HOME(R.id.page_home, R.string.home, Screens.HomeScreen()),
    SEARCH(R.id.page_search, R.string.search, Screens.Search());


    companion object {
        private val map = values().associateBy(Backstacks::tabItemId)

        fun findByTabItemId(tabItemId: Int) = map[tabItemId]
    }
}