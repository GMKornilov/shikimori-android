package com.gmkornilov.shikimori.presentation.filteredanimespage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.di.app.GlobalNavigation
import com.gmkornilov.shikimori.domain.interactors.filteredanimes.FilteredAnimesInteractor
import com.gmkornilov.shikimori.presentation.navigation.arguments.AnimeFilter
import com.gmkornilov.shikimori.presentation.system.rx.SchedulersProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FilteredAnimesViewModelFactory (
    private val animeFilter: AnimeFilter,
    private val filteredAnimesViewModelAssistedFactory: FilteredAnimesViewModelAssistedFactory,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return filteredAnimesViewModelAssistedFactory.create(animeFilter) as T
    }
}

@AssistedFactory
interface FilteredAnimesViewModelAssistedFactory {
    fun create(filter: AnimeFilter): FilteredAnimesViewModel
}