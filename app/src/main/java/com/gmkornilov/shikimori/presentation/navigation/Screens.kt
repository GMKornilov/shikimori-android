package com.gmkornilov.shikimori.presentation.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.gmkornilov.shikimori.presentation.animepage.AnimePageFragment
import com.gmkornilov.shikimori.presentation.filteredanimespage.FilteredAnimesFragment
import com.gmkornilov.shikimori.presentation.mainpage.MainFragment
import com.gmkornilov.shikimori.presentation.navigation.arguments.AnimeFilter
import com.gmkornilov.shikimori.presentation.searchpage.SearchPageFragment

object Screens {
    fun HomeScreen() = FragmentScreen {
        MainFragment.newInstance()
    }

    fun AnimeScreen(id: Long) = FragmentScreen {
        AnimePageFragment.newInstance(id)
    }

    fun FilteredAnimesScreen(filter: AnimeFilter) = FragmentScreen {
        FilteredAnimesFragment.newInstance(filter)
    }

    fun Search() = FragmentScreen {
        SearchPageFragment.newInstance()
    }
}