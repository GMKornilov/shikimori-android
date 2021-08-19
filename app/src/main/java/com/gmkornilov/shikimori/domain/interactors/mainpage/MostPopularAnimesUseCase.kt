package com.gmkornilov.shikimori.domain.interactors.mainpage

import com.gmkornilov.shikimori.domain.interactors.SingleUseCase
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimeOrder
import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import com.gmkornilov.shikimori.domain.repositories.AnimeRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

/**
 * Use case for getting most popular animes
 */
class MostPopularAnimesUseCase @Inject constructor(
    private val animeRepository: AnimeRepository,
) : SingleUseCase<Unit, List<@JvmSuppressWildcards AnimePreview>> {
    override fun buildSingle(params: Unit): Single<List<AnimePreview>> {
        return Single.fromCallable {
            val filter = AnimeFilter.Builder()
                .order(AnimeOrder.POPULARITY)
                .limit(10)
                .build()
            animeRepository.animesByFilter(filter)
        }
    }
}