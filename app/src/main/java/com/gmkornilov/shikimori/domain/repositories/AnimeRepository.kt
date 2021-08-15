package com.gmkornilov.shikimori.domain.repositories

import com.gmkornilov.shikimori.domain.models.common.AnimeInfo

interface AnimeRepository {
    fun nowOnScreensAnimes(): List<AnimeInfo>

    fun announcementsAnimes(): List<AnimeInfo>

    fun mostPopularAnimes(): List<AnimeInfo>

    fun mostRatedAnimes(): List<AnimeInfo>

    fun animeById(id: Long): AnimeInfo

    fun animesBySearchString(searchString: String): List<AnimeInfo>

    // TODO: get anime by filter
}