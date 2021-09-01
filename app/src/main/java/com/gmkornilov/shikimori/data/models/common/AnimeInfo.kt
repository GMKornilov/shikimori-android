package com.gmkornilov.shikimori.data.models.common

import com.gmkornilov.shikimori.data.models.serializers.DateSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*
import com.gmkornilov.shikimori.domain.models.common.AnimeInfo as DomainAnimeInfo

@ExperimentalSerializationApi
@Serializable
data class AnimeInfo(
    val id: Long,
    val name: String,
    @SerialName("russian") val russianName: String,
    @SerialName("image") val imageInfo: ImageInfo,
    val url: String,
    val kind: AnimeKind?,
    val score: Float,
    val status: AnimeStatus,
    val episodes: Int,
    @SerialName("episodes_aired") val episodesAired: Int,
    @Serializable(with = DateSerializer::class) @SerialName("aired_on") val airedOn: Date? = null,
    @Serializable(with = DateSerializer::class) @SerialName("released_on") val releasedOn: Date? = null,
    val rating: AnimeRating,
    val english: List<String?>,
    val japanese: List<String?>,
    val synonyms: List<String?>,
    @SerialName("license_name_ru") val ruLicenseName: String? = null,
    val duration: Int,
    val description: String? = null,
    @SerialName("description_html") val descriptionHtml: String? = null,
    @SerialName("description_source") val descriptionSource: String? = null,
    val franchise: String? = null,
    val favoured: Boolean,
    val anons: Boolean,
    val ongoing: Boolean,
    @SerialName("thread_id") val threadId: Long,
    @SerialName("topic_id") val topicId: Long,
    @SerialName("myanimelist_id") val myAnimeListId: Long? = null,
    @SerialName("rates_scores_stats") val ratesScoresStats: List<NamedStat>,
    @SerialName("rates_statuses_stats") val ratesStatusesStats: List<NamedStat>,
    @Serializable(with = DateSerializer::class) @SerialName("updated_at") val updatedAt: Date? = null,
    @Serializable(with = DateSerializer::class) @SerialName("next_episode_at") val nextEpisodeAt: Date? = null,
    val fansubbers: List<String>? = null,
    val fandubbers: List<String>? = null,
    val genres: List<Genre>? = null,
    val studios: List<Studio>? = null,
    val videos: List<String>? = null,
    val screenshots: List<Screenshot>? = null,
    @SerialName("user_rate") val userRate: Float? = null,
)

@ExperimentalSerializationApi
fun AnimeInfo.toDomainAnimeInfo(): DomainAnimeInfo {
    return DomainAnimeInfo(
        id,
        name,
        russianName,
        imageInfo.toDomainImageInfo(),
        url,
        kind?.toDomainAnimeKind(),
        score,
        status.toDomainAnimeStatus(),
        episodes,
        episodesAired,
        airedOn,
        releasedOn,
        rating.toDomainAnimeRating(),
        english,
        japanese,
        synonyms,
        ruLicenseName,
        duration,
        description,
        descriptionHtml,
        descriptionSource,
        franchise,
        favoured,
        anons,
        ongoing,
        threadId,
        topicId,
        myAnimeListId,
        ratesScoresStats.map { it.toDomainNamedStat() },
        ratesStatusesStats.map { it.toDomainNamedStat() },
        updatedAt,
        nextEpisodeAt,
        fansubbers,
        fandubbers,
        genres?.map { it.toDomainGenre() },
        studios?.map { it.toDomainStudio() },
        videos,
        screenshots?.map { it.toDomainScreenshot() },
        userRate
    )
}
