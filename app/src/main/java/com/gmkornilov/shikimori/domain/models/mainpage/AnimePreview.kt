package com.gmkornilov.shikimori.domain.models.mainpage

import com.gmkornilov.shikimori.domain.models.common.AnimeKind
import com.gmkornilov.shikimori.domain.models.common.AnimeStatus
import com.gmkornilov.shikimori.domain.models.common.ImageInfo
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimePreview(
    val id: Long,
    val name: String,
    @SerialName("russian") val russianName: String,
    @SerialName("image") val imageInfo: ImageInfo,
    val url: String,
    val kind: AnimeKind,
    val score: Float,
    val status: AnimeStatus,
    val episodes: Int = 0,
    @SerialName("episodes_aired") val episodesAired: Int = 0,
    @SerialName("aired_on") val airedOn: LocalDate,
    @SerialName("released_on") val releasedOn: LocalDate? = null,
)