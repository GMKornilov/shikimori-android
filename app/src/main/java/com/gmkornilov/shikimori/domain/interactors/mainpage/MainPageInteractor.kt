package com.gmkornilov.shikimori.domain.interactors.mainpage

import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import io.reactivex.rxjava3.core.Single

/**
 * Interactor for main page
 */
interface MainPageInteractor {
    /**
     * Loads most popular animes, which are now ongoing
     *
     * @return [Single] emitting list of most popular ongoing anime previews
     */
    fun loadNowOnScreens(): Single<List<AnimePreview>>

    /**
     * Loads most popular anime announcements
     *
     * @return [Single] emitting list of most popular anime announcements anime previews
     */
    fun loadAnnouncements(): Single<List<AnimePreview>>

    /**
     * Loads most popular animes
     *
     * @return [Single] emitting list of most popular anime previews
     */
    fun loadMostPopular(): Single<List<AnimePreview>>

    /**
     * Loads most rated animes
     *
     * @return [Single] emitting list of most rated anime previews
     */
    fun loadMostRated(): Single<List<AnimePreview>>
}