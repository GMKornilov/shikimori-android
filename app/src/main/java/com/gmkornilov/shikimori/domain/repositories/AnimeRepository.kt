package com.gmkornilov.shikimori.domain.repositories

import androidx.annotation.WorkerThread
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import kotlinx.serialization.ExperimentalSerializationApi

interface AnimeRepository {
    /**
     * Gets animes by given [filter].
     *
     * @param [filter] given filter for filtering data from data sources
     * @param [needsRefresh] set this to true, if you need to refresh data from remote server
     *
     * @return list of anime previews which matches [filter]
     */
    @WorkerThread
    @ExperimentalSerializationApi
    fun animesByFilter(filter: AnimeFilter, needsRefresh: Boolean = false): List<AnimePreview>
}