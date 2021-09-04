package com.gmkornilov.shikimori.domain.models.common

import java.util.*

/**
 * Preview information about anime
 */
data class AnimePreview(
    val id: Long,
    val name: String,
    val russianName: String,
    val imageInfo: ImageInfo,
    val url: String,
    val kind: AnimeKind?,
    val score: Float,
    val status: AnimeStatus,
    val episodes: Int = 0,
    val episodesAired: Int = 0,
    val airedOn: Date? = null,
    val releasedOn: Date? = null,
)