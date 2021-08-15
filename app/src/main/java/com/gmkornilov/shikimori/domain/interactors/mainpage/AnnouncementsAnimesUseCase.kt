package com.gmkornilov.shikimori.domain.interactors.mainpage

import com.gmkornilov.shikimori.domain.interactors.SingleUseCase
import com.gmkornilov.shikimori.domain.models.common.AnimeInfo
import com.gmkornilov.shikimori.domain.models.mainpage.AnimePreview
import com.gmkornilov.shikimori.domain.models.mapper.TypeDataMapper
import com.gmkornilov.shikimori.domain.repositories.AnimeRepository
import io.reactivex.rxjava3.core.Single
import kotlin.jvm.JvmSuppressWildcards
import javax.inject.Inject

class AnnouncementsAnimesUseCase @Inject constructor(
    private val animeRepository: AnimeRepository,
    private val animePreviewDataMapper: TypeDataMapper<AnimeInfo, AnimePreview>,
) : SingleUseCase<@JvmSuppressWildcards Unit, List<AnimePreview>> {
    override fun buildSingle(params: Unit): Single<List<AnimePreview>> {
        return Single.fromCallable {
            val announcements = animeRepository.announcementsAnimes()
            animePreviewDataMapper.mapList(announcements)
        }
    }
}