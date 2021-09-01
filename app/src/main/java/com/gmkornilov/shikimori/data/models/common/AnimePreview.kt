package com.gmkornilov.shikimori.data.models.common

import com.gmkornilov.shikimori.data.models.serializers.DateSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*
import com.gmkornilov.shikimori.domain.models.common.AnimePreview as DomainAnimePreview

@ExperimentalSerializationApi
@Serializable
data class AnimePreview(
    val id: Long,
    val name: String,
    @SerialName("russian") val russianName: String,
    @SerialName("image") val imageInfo: ImageInfo,
    val url: String,
    val kind: AnimeKind?,
    val score: Float,
    val status: AnimeStatus,
    val episodes: Int = 0,
    @SerialName("episodes_aired") val episodesAired: Int = 0,
    @Serializable(with = DateSerializer::class) @SerialName("aired_on") val airedOn: Date? = null,
    @Serializable(with = DateSerializer::class) @SerialName("released_on") val releasedOn: Date? = null,
)

fun AnimePreview.toDomainAnimePreview() : DomainAnimePreview {
    return DomainAnimePreview(
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
    )
}