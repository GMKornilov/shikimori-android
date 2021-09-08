package com.gmkornilov.shikimori.domain.models.common

import java.util.*

/**
 * Information about specific anime
 */
data class AnimeInfo(
    val id: Long,
    val name: String,
    val russianName: String,
    val imageInfo: ImageInfo,
    val url: String,
    val kind: AnimeKind?,
    val score: Float,
    val status: AnimeStatus,
    val episodes: Int,
    val episodesAired: Int,
    val airedOn: Date? = null,
    val releasedOn: Date? = null,
    val rating: AnimeRating,
    val english: List<String?>,
    val japanese: List<String?>,
    val synonyms: List<String?>,
    val ruLicenseName: String? = null,
    val duration: Int,
    val description: String? = null,
    val descriptionHtml: String? = null,
    val descriptionSource: String? = null,
    val franchise: String? = null,
    val favoured: Boolean,
    val anons: Boolean,
    val ongoing: Boolean,
    val threadId: Long,
    val topicId: Long,
    val myAnimeListId: Long? = null,
    val ratesScoresStats: List<NamedStat>,
    val ratesStatusesStats: List<NamedStat>,
    val updatedAt: Date? = null,
    val nextEpisodeAt: Date? = null,
    val fansubbers: List<String>? = null,
    val fandubbers: List<String>? = null,
    val genres: List<Genre>? = null,
    val studios: List<Studio>? = null,
    val videos: List<Video>? = null,
    val screenshots: List<Screenshot>? = null,
    val userRate: Float? = null,
)
