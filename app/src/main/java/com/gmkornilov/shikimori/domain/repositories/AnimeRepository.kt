package com.gmkornilov.shikimori.domain.repositories

import androidx.annotation.WorkerThread
import com.gmkornilov.shikimori.data.http.RequestResult
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimeInfo
import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import io.reactivex.rxjava3.core.Single
import kotlinx.serialization.ExperimentalSerializationApi

interface AnimeRepository {
    /**
     * Gets anime previews by given [filter].
     *
     * @param [filter] given filter for filtering data from data sources
     * @param [needsRefresh] set this to true, if you need to refresh data from remote server
     *
     * @return list of anime previews which matches [filter]
     */
    @WorkerThread
    @ExperimentalSerializationApi
    fun animesByFilter(filter: AnimeFilter, needsRefresh: Boolean = false): Single<RequestResult<List<AnimePreview>>>

    /**
     * Gets information about anime bu given [id]
     *
     * @param [id] id of anime
     *
     * @return [AnimeInfo] about anime
     */
    @WorkerThread
    @ExperimentalSerializationApi
    fun animeById(id: Long): Single<RequestResult<AnimeInfo>>
}