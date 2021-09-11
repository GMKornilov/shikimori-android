package com.gmkornilov.shikimori.domain.interactors.mainpage

import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import io.reactivex.rxjava3.core.Single

/**
 * Interactor for main page
 */
interface MainPageInteractor {

    /**
     * Filter used in [loadNowOnScreens] for loading ongoing animes
     */
    val nowOnScreensFilter: AnimeFilter

    /**
     * Filter used in [loadAnnouncements] for loading announced animes
     */
    val announcementsFilter: AnimeFilter

    /**
     * Filter used in [loadMostPopular] for loading most popular animes
     */
    val mostPopularFilter: AnimeFilter

    /**
     * Filter used in [loadMostRated] for loading most rated animes
     */
    val mostRatedFilter: AnimeFilter

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