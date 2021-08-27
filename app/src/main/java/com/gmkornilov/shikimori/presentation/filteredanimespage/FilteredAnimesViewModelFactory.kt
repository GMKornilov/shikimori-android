package com.gmkornilov.shikimori.presentation.filteredanimespage

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.di.app.GlobalNavigation
import com.gmkornilov.shikimori.domain.interactors.filteredanimes.FilteredAnimesInteractor
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.presentation.system.rx.SchedulersProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FilteredAnimesViewModelFactory @AssistedInject constructor(
    private val filteredAnimesInteractor: FilteredAnimesInteractor,
    private val schedulersProvider: SchedulersProvider,
    @GlobalNavigation private val router: Router,
    @Assisted private val filter: AnimeFilter,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FilteredAnimesViewModel(
            filteredAnimesInteractor = filteredAnimesInteractor,
            schedulersProvider = schedulersProvider,
            filter = filter,
            router = router,
        ) as T
    }
}

@AssistedFactory
interface FilteredAnimesViewModelAssistedFactory {
    fun create(filter: AnimeFilter): FilteredAnimesViewModelFactory
}