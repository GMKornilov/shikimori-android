package com.gmkornilov.shikimori.domain.repositories

import androidx.annotation.WorkerThread
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimePreview

interface AnimeRepository {
    @WorkerThread
    fun animesByFilter(filter: AnimeFilter, needsRefresh: Boolean = false): List<AnimePreview>
}