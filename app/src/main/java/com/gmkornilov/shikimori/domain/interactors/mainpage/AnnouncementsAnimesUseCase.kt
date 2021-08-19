package com.gmkornilov.shikimori.domain.interactors.mainpage

import com.gmkornilov.shikimori.domain.interactors.SingleUseCase
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimeOrder
import com.gmkornilov.shikimori.domain.models.common.AnimeStatus
import com.gmkornilov.shikimori.domain.models.mainpage.AnimePreview
import com.gmkornilov.shikimori.domain.repositories.AnimeRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AnnouncementsAnimesUseCase @Inject constructor(
    private val animeRepository: AnimeRepository,
) : SingleUseCase<Unit, List<@JvmSuppressWildcards AnimePreview>> {
    override fun buildSingle(params: Unit): Single<List<AnimePreview>> {
        return Single.fromCallable {
            val filter = AnimeFilter.Builder()
                .status(AnimeStatus.ANONS)
                .order(AnimeOrder.POPULARITY)
                .limit(10)
                .build()
            animeRepository.animesByFilter(filter)
        }
    }
}