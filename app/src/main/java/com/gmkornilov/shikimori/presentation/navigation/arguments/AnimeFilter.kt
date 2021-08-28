package com.gmkornilov.shikimori.presentation.navigation.arguments

import android.os.Parcelable
import com.gmkornilov.shikimori.domain.models.common.*
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter as DomainAnimeFilter
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable

fun DomainAnimeFilter.toPresentationAnimeFilter(): AnimeFilter {
    return AnimeFilter(
        page,
        limit,
        order,
        kind,
        status,
        season,
        minimalScore,
        duration,
        rating,
        genreIds,
        studioIds,
        franchises,
        censored,
        ids,
        excludeIds,
        searchString
    )
}

fun AnimeFilter.toDomainAnimeFilter(): DomainAnimeFilter {
    return DomainAnimeFilter(
        page,
        limit,
        order,
        kind,
        status,
        season,
        minimalScore,
        duration,
        rating,
        genreIds,
        studioIds,
        franchises,
        censored,
        ids,
        excludeIds,
        searchString
    )
}