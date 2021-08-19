package com.gmkornilov.shikimori.domain.repositories

import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimePreview

interface AnimeRepository {
    fun animesByFilter(filter: AnimeFilter, needsRefresh: Boolean = false): List<AnimePreview>
}