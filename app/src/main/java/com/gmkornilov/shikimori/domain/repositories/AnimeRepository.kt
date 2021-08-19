package com.gmkornilov.shikimori.domain.repositories

import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.mainpage.AnimePreview

interface AnimeRepository {
    fun animesByFilter(filter: AnimeFilter): List<AnimePreview>
}