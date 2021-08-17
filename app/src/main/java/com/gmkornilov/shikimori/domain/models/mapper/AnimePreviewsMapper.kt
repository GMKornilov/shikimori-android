package com.gmkornilov.shikimori.domain.models.mapper

import com.gmkornilov.shikimori.BuildConfig
import com.gmkornilov.shikimori.domain.models.common.AnimeInfo
import com.gmkornilov.shikimori.domain.models.mainpage.AnimePreview
import javax.inject.Inject

class AnimePreviewsMapper @Inject constructor() : TypeDataMapper<AnimeInfo, AnimePreview> {
    override fun map(from: AnimeInfo): AnimePreview {
        return AnimePreview(from.id, BuildConfig.BASE_URL + from.imageInfo.urlOriginal, from.name)
    }
}