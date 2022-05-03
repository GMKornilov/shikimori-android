package com.gmkornilov.shikimori.presentation.filteredanimespage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.domain.interactors.filteredanimes.FilteredAnimesInteractor
import com.gmkornilov.shikimori.domain.models.common.AnimeOrder
import com.gmkornilov.shikimori.presentation.components.animepreview.AnimePreview
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter as DomainAnimeFilter
import com.gmkornilov.shikimori.presentation.components.animepreview.AnimePreviewClicked
import com.gmkornilov.shikimori.presentation.components.animepreview.toPresentationAnimePreview
import com.gmkornilov.shikimori.presentation.extensions.map
import com.gmkornilov.shikimori.presentation.extensions.switchMap
import com.gmkornilov.shikimori.presentation.lazyloaders.SingleLazyLoader
import com.gmkornilov.shikimori.presentation.navigation.Screens
import com.gmkornilov.shikimori.presentation.navigation.arguments.AnimeFilter
import com.gmkornilov.shikimori.presentation.navigation.arguments.toDomainAnimeFilter
import com.gmkornilov.shikimori.presentation.navigation.arguments.toPresentationAnimeFilter
import com.gmkornilov.shikimori.presentation.system.rx.SchedulersProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class FilteredAnimesViewModel @AssistedInject constructor(
    private val filteredAnimesInteractor: FilteredAnimesInteractor,
    private val schedulersProvider: SchedulersProvider,
    @Assisted private val router: Router,
    @Assisted private val filter: AnimeFilter?,
) : ViewModel(), AnimePreviewClicked {
    private val filterSubject: MutableLiveData<AnimeFilter> =
        MutableLiveData(filter ?: getDefaultFilter())

    private val loadingData = filterSubject.map { animeFilter ->
        SingleLazyLoader(
            schedulersProvider,
            { filteredAnimesInteractor.loadAnimesByFilter(animeFilter.toDomainAnimeFilter()) }
        ) { list ->
            list.map {
                it.toPresentationAnimePreview()
            }
        }
    }

    val loading = loadingData.switchMap { it.loading }
    val exception = loadingData.switchMap { it.exception }
    val loadedWithoutErrors = loadingData.switchMap { it.loadedWithoutErrors }
    val values = loadingData.switchMap { it.values }

    fun load() {
        loadingData.value?.load()
    }

    override fun onCleared() {
        super.onCleared()
        loadingData.value?.destroy()
    }

    override fun onClicked(animePreview: AnimePreview) {
        router.navigateTo(Screens.AnimeScreen(animePreview))
    }

    private fun getDefaultFilter(): AnimeFilter {
        return DomainAnimeFilter.Builder()
            .order(AnimeOrder.POPULARITY)
            .limit(10)
            .build()
            .toPresentationAnimeFilter()
    }
}