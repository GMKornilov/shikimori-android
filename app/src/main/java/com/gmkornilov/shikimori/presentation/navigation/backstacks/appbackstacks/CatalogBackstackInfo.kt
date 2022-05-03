package com.gmkornilov.shikimori.presentation.navigation.backstacks.appbackstacks

import com.github.terrakok.cicerone.Screen
import com.gmkornilov.shikimori.presentation.filteredanimespage.FilteredAnimesTitleType
import com.gmkornilov.shikimori.presentation.navigation.Screens
import com.gmkornilov.shikimori.presentation.navigation.backstacks.BackstackInfo
import com.gmkornilov.shikimori.presentation.navigation.backstacks.Backstacks
import kotlinx.parcelize.Parcelize

@Parcelize
class CatalogBackstackInfo: BackstackInfo {
    override fun getBackstackName(): String {
        return Backstacks.CATALOG.toString()
    }

    override fun getRootScreen(): Screen {
        return Screens.FilteredAnimesScreen(
            FilteredAnimesTitleType.FILTERS,
            backAvailable = false,
        )
    }
}