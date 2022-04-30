package com.gmkornilov.shikimori.presentation.searchpage

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.data.http.RequestResult
import com.gmkornilov.shikimori.domain.interactors.searchpage.SearchPageInteractor
import com.gmkornilov.shikimori.presentation.components.animepreview.AnimePreview
import com.gmkornilov.shikimori.domain.models.common.AnimePreview as DomainAnimePreview
import com.gmkornilov.shikimori.presentation.components.animepreview.AnimePreviewClicked
import com.gmkornilov.shikimori.presentation.components.animepreview.toPresentationAnimePreview
import com.gmkornilov.shikimori.presentation.lazyloaders.SingleLazyLoader
import com.gmkornilov.shikimori.presentation.navigation.Screens
import com.gmkornilov.shikimori.presentation.system.rx.SchedulersProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.core.Single

class SearchPageViewModel @AssistedInject constructor(
    private val searchPageInteractor: SearchPageInteractor,
    private val schedulersProvider: SchedulersProvider,
    @Assisted private val router: Router,
) : ViewModel(), AnimePreviewClicked {
    private var query: String? = null

    val searchLoadingData: SingleLazyLoader<List<DomainAnimePreview>, List<AnimePreview>> by lazy {
        SingleLazyLoader(
            schedulersProvider,
            {
                val currentQuery = query
                if (currentQuery != null) {
                    searchPageInteractor.loadAnimesByQuery(currentQuery, SEARCH_LIMIT)
                } else {
                    Single.just(RequestResult.Success(emptyList()))
                }
            }
        ) { list ->
            list.map {
                it.toPresentationAnimePreview()
            }
        }
    }

    fun loadPreviews(query: String) {
        this.query = query
        searchLoadingData.load()
    }

    override fun onCleared() {
        super.onCleared()
        searchLoadingData.destroy()
    }

    override fun onClicked(animePreview: AnimePreview) {
        router.navigateTo(Screens.AnimeScreen(animePreview))
    }

    companion object {
        private const val SEARCH_LIMIT = 16
    }
}