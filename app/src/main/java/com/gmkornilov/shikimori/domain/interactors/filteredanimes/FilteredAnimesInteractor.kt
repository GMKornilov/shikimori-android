package com.gmkornilov.shikimori.domain.interactors.filteredanimes

import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import io.reactivex.rxjava3.core.Single

/**
 * Interactor for filtered animes page
 */
interface FilteredAnimesInteractor {
    /**
     * Gets animes by given [filter].
     *
     * @param [filter] given filter for filtering data from data sources
     * @param [needsRefresh] set this to true, if you need to refresh data from remote server
     *
     * @return [Single] emitting list of anime previews which matches [filter]
     */
    fun loadAnimesByFilter(filter: AnimeFilter, needsRefresh: Boolean = false): Single<List<AnimePreview>>
}