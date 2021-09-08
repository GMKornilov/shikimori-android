package com.gmkornilov.shikimori.presentation.filteredanimespage

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.domain.interactors.filteredanimes.FilteredAnimesInteractor
import com.gmkornilov.shikimori.presentation.components.animepreview.AnimePreview
import com.gmkornilov.shikimori.domain.models.common.AnimePreview as DomainAnimePreview
import com.gmkornilov.shikimori.presentation.components.animepreview.AnimePreviewClicked
import com.gmkornilov.shikimori.presentation.components.animepreview.toPresentationAnimePreview
import com.gmkornilov.shikimori.presentation.lazyloaders.SingleLazyLoader
import com.gmkornilov.shikimori.presentation.navigation.Screens
import com.gmkornilov.shikimori.presentation.navigation.arguments.AnimeFilter
import com.gmkornilov.shikimori.presentation.navigation.arguments.toDomainAnimeFilter
import com.gmkornilov.shikimori.presentation.system.rx.SchedulersProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class FilteredAnimesViewModel @AssistedInject constructor(
    private val filteredAnimesInteractor: FilteredAnimesInteractor,
    private val schedulersProvider: SchedulersProvider,
    @Assisted private val router: Router,
    @Assisted private val filter: AnimeFilter,
) : ViewModel(), AnimePreviewClicked {
    val loadingData: SingleLazyLoader<List<DomainAnimePreview>, List<AnimePreview>> by lazy {
        SingleLazyLoader(
            schedulersProvider,
            { filteredAnimesInteractor.loadAnimesByFilter(filter.toDomainAnimeFilter()) }
        ) { list ->
            list.map {
                it.toPresentationAnimePreview()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        loadingData.destroy()
    }

    override fun onClicked(animePreview: AnimePreview) {
        router.navigateTo(Screens.AnimeScreen(animePreview))
    }
}