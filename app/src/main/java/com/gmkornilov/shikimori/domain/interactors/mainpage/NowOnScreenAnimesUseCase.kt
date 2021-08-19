package com.gmkornilov.shikimori.domain.interactors.mainpage

import com.gmkornilov.shikimori.domain.interactors.SingleUseCase
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimeOrder
import com.gmkornilov.shikimori.domain.models.common.AnimeStatus
import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import com.gmkornilov.shikimori.domain.repositories.AnimeRepository
import io.reactivex.rxjava3.core.Single
import java.util.*
import javax.inject.Inject

/**
 * Use case for loading previews about animes, which are now in ongoing status
 */
class NowOnScreenAnimesUseCase @Inject constructor(
    private val animeRepository: AnimeRepository,
) : SingleUseCase<Unit, List<@JvmSuppressWildcards AnimePreview>> {
    override fun buildSingle(params: Unit): Single<List<AnimePreview>> {
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
}