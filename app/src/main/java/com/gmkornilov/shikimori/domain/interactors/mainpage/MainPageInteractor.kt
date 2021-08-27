package com.gmkornilov.shikimori.domain.interactors.mainpage

import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import io.reactivex.rxjava3.core.Single

interface MainPageInteractor {
    fun loadNowOnScreens(): Single<List<AnimePreview>>

    fun loadAnnouncements(): Single<List<AnimePreview>>

    fun loadMostPopular(): Single<List<AnimePreview>>

    fun loadMostRated(): Single<List<AnimePreview>>
}