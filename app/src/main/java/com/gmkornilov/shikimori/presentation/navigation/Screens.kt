package com.gmkornilov.shikimori.presentation.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.presentation.animepage.AnimePageFragment
import com.gmkornilov.shikimori.presentation.filteredanimes.FilteredAnimesFragment
import com.gmkornilov.shikimori.presentation.mainpage.MainFragment

object Screens {
    fun MainScreen() = FragmentScreen {
        MainFragment.newInstance()
    }

    fun AnimeScreen(id: Long) = FragmentScreen {
        AnimePageFragment.newInstance(id)
    }

    fun FilteredAnimesScreen(filter: AnimeFilter) = FragmentScreen {
        FilteredAnimesFragment.newInstance(filter)
    }
}