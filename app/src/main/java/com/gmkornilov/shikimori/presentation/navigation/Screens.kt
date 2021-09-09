package com.gmkornilov.shikimori.presentation.navigation

import android.content.Intent
import android.net.Uri
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.gmkornilov.shikimori.presentation.animepage.AnimePageFragment
import com.gmkornilov.shikimori.presentation.filteredanimespage.FilteredAnimesFragment
import com.gmkornilov.shikimori.presentation.components.animepreview.AnimePreview
import com.gmkornilov.shikimori.presentation.mainpage.MainFragment
import com.gmkornilov.shikimori.presentation.navigation.arguments.AnimeFilter
import com.gmkornilov.shikimori.presentation.navigation.backstacks.BackstackFragment
import com.gmkornilov.shikimori.presentation.navigation.backstacks.BackstackInfo
import com.gmkornilov.shikimori.presentation.searchpage.SearchPageFragment

object Screens {
    fun HomeScreen() = FragmentScreen {
        MainFragment.newInstance()
    }

    fun AnimeScreen(animePreview: AnimePreview) = FragmentScreen {
        AnimePageFragment.newInstance(animePreview)
    }

    fun FilteredAnimesScreen(filter: AnimeFilter) = FragmentScreen {
        FilteredAnimesFragment.newInstance(filter)
    }

    fun Search() = FragmentScreen {
        SearchPageFragment.newInstance()
    }

    fun Backstack(backstackInfo: BackstackInfo) = FragmentScreen {
        BackstackFragment.newInstance(backstackInfo)
    }

    fun Video(url: String) = ActivityScreen {
        Intent(Intent.ACTION_VIEW, Uri.parse(url))
    }
}