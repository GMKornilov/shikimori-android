package com.gmkornilov.shikimori.presentation.mainpage

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.domain.interactors.mainpage.MainPageInteractor
import com.gmkornilov.shikimori.presentation.components.animepreview.AnimePreview
import com.gmkornilov.shikimori.presentation.components.animepreview.AnimePreviewClicked
import com.gmkornilov.shikimori.presentation.components.animepreview.toPresentationAnimePreview
import com.gmkornilov.shikimori.presentation.filteredanimespage.FilteredAnimesTitleType
import com.gmkornilov.shikimori.presentation.lazyloaders.SingleLazyLoader
import com.gmkornilov.shikimori.presentation.navigation.Screens
import com.gmkornilov.shikimori.presentation.navigation.arguments.toPresentationAnimeFilter
import com.gmkornilov.shikimori.presentation.system.rx.SchedulersProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import com.gmkornilov.shikimori.domain.models.common.AnimePreview as DomainAnimePreview

class MainViewModel @AssistedInject constructor(
    private val mainPageInteractor: MainPageInteractor,
    private val schedulersProvider: SchedulersProvider,
    @Assisted private val router: Router,
) : ViewModel(), AnimePreviewClicked {
    val nowOnScreensLoadingData: SingleLazyLoader<List<DomainAnimePreview>, List<AnimePreview>> by lazy {
        SingleLazyLoader(
            schedulersProvider,
            { mainPageInteractor.loadNowOnScreens() }
        ) { list ->
            list.map {
                it.toPresentationAnimePreview()
            }
        }
    }

    val announcementsLoadingData: SingleLazyLoader<List<DomainAnimePreview>, List<AnimePreview>> by lazy {
        SingleLazyLoader(
            schedulersProvider,
            { mainPageInteractor.loadAnnouncements() }
        ) { list ->
            list.map {
                it.toPresentationAnimePreview()
            }
        }
    }

    val mostPopularLoadingData: SingleLazyLoader<List<DomainAnimePreview>, List<AnimePreview>> by lazy {
        SingleLazyLoader(
            schedulersProvider,
            { mainPageInteractor.loadMostPopular() }
        ) { list ->
            list.map {
                it.toPresentationAnimePreview()
            }
        }
    }

    val mostRatedLoadingData: SingleLazyLoader<List<DomainAnimePreview>, List<AnimePreview>> by lazy {
        SingleLazyLoader(
            schedulersProvider,
            { mainPageInteractor.loadMostRated() }
        ) { list ->
            list.map {
                it.toPresentationAnimePreview()
            }
        }
    }

    fun loadAll() {
        nowOnScreensLoadingData.load()
        announcementsLoadingData.load()
        mostPopularLoadingData.load()
        mostRatedLoadingData.load()
    }

    fun nowOnScreensClicked() {
        router.navigateTo(
            Screens.FilteredAnimesScreen(
                FilteredAnimesTitleType.NOW_ON_SCREENS,
                mainPageInteractor.nowOnScreensFilter.toPresentationAnimeFilter()
            )
        )
    }

    fun announcementsClicked() {
        router.navigateTo(
            Screens.FilteredAnimesScreen(
                FilteredAnimesTitleType.ANNOUNCED,
                mainPageInteractor.announcementsFilter.toPresentationAnimeFilter()
            )
        )
    }

    fun mostPopularClicked() {
        router.navigateTo(
            Screens.FilteredAnimesScreen(
                FilteredAnimesTitleType.MOST_POPULAR,
                mainPageInteractor.mostPopularFilter.toPresentationAnimeFilter()
            )
        )
    }

    fun mostRatedClicked() {
        router.navigateTo(
            Screens.FilteredAnimesScreen(
                FilteredAnimesTitleType.MOST_RATED,
                mainPageInteractor.mostRatedFilter.toPresentationAnimeFilter()
            )
        )
    }

    override fun onClicked(animePreview: AnimePreview) {
        router.navigateTo(Screens.AnimeScreen(animePreview))
    }

    override fun onCleared() {
        super.onCleared()

        nowOnScreensLoadingData.destroy()
        announcementsLoadingData.destroy()
        mostPopularLoadingData.destroy()
        mostRatedLoadingData.destroy()
    }
}