package com.gmkornilov.shikimori.data.models.common

import com.gmkornilov.shikimori.domain.models.common.AnimeFilter as DomainAnimeFilter

data class AnimeFilter(
    val page: Int?,
    val limit: Int?,
    val order: AnimeOrder?,
    val kind: AnimeKind?,
    val status: AnimeStatus?,
    val season: String?,
    val minimalScore: Float?,
    val duration: AnimeDuration?,
    val rating: AnimeRating?,
    val genreIds: List<Int>?,
    val studioIds: List<Int>?,
    val franchises: List<String>?,
    val censored: Boolean?,
    // val mylist
    val ids: List<Long>?,
    val excludeIds: List<Long>?,
    val searchString: String?,
)

fun DomainAnimeFilter.toDataAnimeFilter(): AnimeFilter {
    return AnimeFilter(
        page,
        limit,
        order?.toDataAnimeOrder(),
        kind?.toDataAnimeKind(),
        status?.toDataAnimeStatus(),
        season,
        minimalScore,
        duration?.toDataAnimeDuration(),
        rating?.toDataAnimeRating(),
        genreIds,
        studioIds,
        franchises,
        censored,
        ids,
        excludeIds,
        searchString
    )
}