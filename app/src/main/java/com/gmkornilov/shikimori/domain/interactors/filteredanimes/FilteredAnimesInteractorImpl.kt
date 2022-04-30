package com.gmkornilov.shikimori.domain.interactors.filteredanimes

import com.gmkornilov.shikimori.data.http.RequestResult
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import com.gmkornilov.shikimori.domain.repositories.AnimeRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FilteredAnimesInteractorImpl @Inject constructor(
    private val animeRepository: AnimeRepository,
) : FilteredAnimesInteractor {
    override fun loadAnimesByFilter(filter: AnimeFilter, needsRefresh: Boolean): Single<RequestResult<List<AnimePreview>>> {
        return animeRepository.animesByFilter(filter, needsRefresh)
    }
}