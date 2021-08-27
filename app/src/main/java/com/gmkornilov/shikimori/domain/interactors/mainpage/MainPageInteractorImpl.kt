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
): MainPageInteractor {
    override fun loadNowOnScreens(): Single<List<AnimePreview>> {
        return Single.fromCallable {
            val filter = AnimeFilter.Builder()
                .status(AnimeStatus.ONGOING)
                .order(AnimeOrder.POPULARITY)
                .season(Calendar.getInstance().get(Calendar.YEAR).toString())
                .limit(10)
                .build()
            animeRepository.animesByFilter(filter)
        }
    }

    override fun loadAnnouncements(): Single<List<AnimePreview>> {
        return Single.fromCallable {
            val filter = AnimeFilter.Builder()
                .status(AnimeStatus.ANONS)
                .order(AnimeOrder.POPULARITY)
                .limit(10)
                .build()
            animeRepository.animesByFilter(filter)
        }
    }

    override fun loadMostPopular(): Single<List<AnimePreview>> {
        return Single.fromCallable {
            val filter = AnimeFilter.Builder()
                .order(AnimeOrder.POPULARITY)
                .limit(10)
                .build()
            animeRepository.animesByFilter(filter)
        }
    }

    override fun loadMostRated(): Single<List<AnimePreview>> {
        return Single.fromCallable {
            val filter = AnimeFilter.Builder()
                .order(AnimeOrder.RANKED)
                .limit(10)
                .build()
            animeRepository.animesByFilter(filter)
        }
    }
}