package com.gmkornilov.shikimori.domain.interactors.animepage

import com.gmkornilov.shikimori.domain.models.common.AnimeInfo
import io.reactivex.rxjava3.core.Single
import kotlinx.serialization.ExperimentalSerializationApi

interface AnimePageInteractor {
    @ExperimentalSerializationApi
    /**
     * Gets anime info by its [id].
     *
     * @param [id] anime's id
     *
     * @return [Single] emitting information about anime with given [id]
     */
    fun loadAnime(id: Long): Single<AnimeInfo>
}