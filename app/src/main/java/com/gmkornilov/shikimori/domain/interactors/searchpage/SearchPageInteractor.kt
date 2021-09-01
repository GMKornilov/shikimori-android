package com.gmkornilov.shikimori.domain.interactors.searchpage

import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import io.reactivex.rxjava3.core.Single

interface SearchPageInteractor {
    fun loadAnimesByQuery(query: String, limit: Int): Single<List<AnimePreview>>
}