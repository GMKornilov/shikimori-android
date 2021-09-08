package com.gmkornilov.shikimori.data.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.gmkornilov.shikimori.domain.models.common.Video as DomainVideo

/**
 * Information about video
 */
@Serializable
data class Video(
    val id: Long,
    val url: String,
    @SerialName("image_url") val imageUrl: String,
    @SerialName("player_url") val playerUrl: String,
    val name: String,
    val kind: String,
    val hosting: String,
)

/**
 * Convert data model of video to domain model
 */
fun Video.toDomainVideo(): DomainVideo {
    return DomainVideo(id, url, imageUrl, playerUrl, name, kind, hosting)
}