package com.gmkornilov.shikimori.domain.interactors.searchpage

import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import io.reactivex.rxjava3.core.Single

/**
 * Interactor for a search page
 */
interface SearchPageInteractor {
    /**
     * Loads [limits] animes which matches given string
     *
     * @param query query for finding animes
     * @param limit amount of animes to load
     *
     * @return [Single] emitting list of anime previews matching [query]
     */
    fun loadAnimesByQuery(query: String, limit: Int): Single<List<AnimePreview>>
}