package com.gmkornilov.shikimori.domain.interactors.animepage

import com.gmkornilov.shikimori.data.http.RequestResult
import com.gmkornilov.shikimori.domain.models.common.AnimeInfo
import com.gmkornilov.shikimori.domain.repositories.AnimeRepository
import io.reactivex.rxjava3.core.Single
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

class AnimePageInteractorImpl @Inject constructor(
    private val repository: AnimeRepository,
): AnimePageInteractor {
    @ExperimentalSerializationApi
    override fun loadAnime(id: Long): Single<RequestResult<AnimeInfo>> {
        return repository.animeById(id)
    }
}