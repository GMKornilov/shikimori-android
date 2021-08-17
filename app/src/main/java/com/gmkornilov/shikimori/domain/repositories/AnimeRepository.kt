package com.gmkornilov.shikimori.domain.repositories

import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimeInfo

interface AnimeRepository {
    fun animesByFilter(filter: AnimeFilter): List<AnimeInfo>
}