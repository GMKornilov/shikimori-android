package com.gmkornilov.shikimori.domain.interactors.filteredanimes

import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import io.reactivex.rxjava3.core.Single

interface FilteredAnimesInteractor {
    fun loadAnimesByFilter(filter: AnimeFilter, needsRefresh: Boolean = false): Single<List<AnimePreview>>
}