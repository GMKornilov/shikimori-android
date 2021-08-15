package com.gmkornilov.shikimori.domain.interactors.mainpage

import com.gmkornilov.shikimori.domain.interactors.SingleUseCase
import com.gmkornilov.shikimori.domain.models.common.AnimeInfo
import com.gmkornilov.shikimori.domain.models.mainpage.AnimePreview
import com.gmkornilov.shikimori.domain.models.mapper.TypeDataMapper
import com.gmkornilov.shikimori.domain.repositories.AnimeRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject
import kotlin.jvm.JvmSuppressWildcards

/**
 * Use case for loading previews about animes, which are now in ongoing status
 */
class NowOnScreenAnimesUseCase @Inject constructor(
    private val animeRepository: AnimeRepository,
    private val animePreviewDataMapper: TypeDataMapper<AnimeInfo, AnimePreview>,
) : SingleUseCase<@JvmSuppressWildcards Unit, List<AnimePreview>> {
    override fun buildSingle(params: Unit): Single<List<AnimePreview>> {
        return Single.fromCallable {
            val animeInfos = animeRepository.nowOnScreensAnimes()
            animePreviewDataMapper.mapList(animeInfos)
        }
    }
}