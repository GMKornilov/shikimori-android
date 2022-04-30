package com.gmkornilov.shikimori.domain.interactors.searchpage

import com.gmkornilov.shikimori.data.http.RequestResult
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import com.gmkornilov.shikimori.domain.repositories.AnimeRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SearchPageInteractorImpl @Inject constructor(
    private val repository: AnimeRepository,
) : SearchPageInteractor {
    override fun loadAnimesByQuery(
        query: String,
        limit: Int
    ): Single<RequestResult<List<AnimePreview>>> {
        val filter = AnimeFilter.Builder()
            .searchString(query)
            .limit(limit)
            .build()
        return repository.animesByFilter(filter)
    }
}