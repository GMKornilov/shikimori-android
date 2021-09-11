package com.gmkornilov.shikimori.domain.interactors.mainpage

import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimeOrder
import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import com.gmkornilov.shikimori.domain.models.common.AnimeStatus
import com.gmkornilov.shikimori.domain.repositories.AnimeRepository
import io.reactivex.rxjava3.core.Single
import java.util.*
import javax.inject.Inject

class MainPageInteractorImpl @Inject constructor(
    private val animeRepository: AnimeRepository,
) : MainPageInteractor {
    override val nowOnScreensFilter = AnimeFilter.Builder()
        .status(AnimeStatus.ONGOING)
        .order(AnimeOrder.POPULARITY)
        .season(Calendar.getInstance().get(Calendar.YEAR).toString())
        .limit(10)
        .build()

    override val announcementsFilter = AnimeFilter.Builder()
        .status(AnimeStatus.ANONS)
        .order(AnimeOrder.POPULARITY)
        .limit(10)
        .build()

    override val mostPopularFilter = AnimeFilter.Builder()
        .order(AnimeOrder.POPULARITY)
        .limit(10)
        .build()

    override val mostRatedFilter = AnimeFilter.Builder()
        .order(AnimeOrder.RANKED)
        .limit(10)
        .build()

    override fun loadNowOnScreens(): Single<List<AnimePreview>> {
        return Single.fromCallable {
            animeRepository.animesByFilter(nowOnScreensFilter)
        }
    }

    override fun loadAnnouncements(): Single<List<AnimePreview>> {
        return Single.fromCallable {
            animeRepository.animesByFilter(announcementsFilter)
        }
    }

    override fun loadMostPopular(): Single<List<AnimePreview>> {
        return Single.fromCallable {
            animeRepository.animesByFilter(mostPopularFilter)
        }
    }

    override fun loadMostRated(): Single<List<AnimePreview>> {
        return Single.fromCallable {
            animeRepository.animesByFilter(mostRatedFilter)
        }
    }
}