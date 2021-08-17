package com.gmkornilov.shikimori.domain.models.common

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeInfo(
    val id: Long,
    val name: String,
    @SerialName("russian") val russianName: String,
    @SerialName("image") val imageInfo: ImageInfo,
    val url: String? = null,
    val kind: AnimeKind? = null,
    val score: Float? = null,
    val status: AnimeStatus? = null,
    val episodes: Int? = null,
    @SerialName("episodes_aired") val episodesAired: Int? = null,
    @SerialName("aired_on") val airedOn: LocalDate? = null,
    @SerialName("released_on") val releasedOn: LocalDate? = null,
    val rating: AnimeRating? = null,
    val english: List<String?>? = null,
    val japanese: List<String?>? = null,
    val synonyms: List<String?>? = null,
    @SerialName("license_name_ru") val ruLicenseName: String? = null,
    val duration: Int? = null,
    val description: String? = null,
    @SerialName("description_html") val descriptionHtml: String? = null,
    @SerialName("description_source") val descriptionSource: String? = null,
    val franchise: String? = null,
    val favoured: Boolean? = null,
    val anons: Boolean? = null,
    val ongoing: Boolean? = null,
    @SerialName("thread_id") val threadId: Long? = null,
    @SerialName("topic_id") val topicId: Long? = null,
    @SerialName("myanimelist_id") val myAnimeListId: Long? = null,
    @SerialName("rates_scores_stats") val ratesScoresStats: List<NamedStat>? = null,
    @SerialName("rates_statuses_stats") val ratesStatusesStats: List<NamedStat>? = null,
    @SerialName("updated_at") val updatedAt: LocalDate? = null,
    @SerialName("next_episode_at") val nextEpisodeAt: LocalDate? = null,
    val fansubbers: List<String>? = null,
    val fandubbers: List<String>? = null,
    val genres: List<Genre>? = null,
    val studios: List<Studio>? = null,
    val videos: List<String>? = null,
    val screenshots: List<Screenshot>? = null,
    @SerialName("user_rate") val userRate: Float? = null,
)
