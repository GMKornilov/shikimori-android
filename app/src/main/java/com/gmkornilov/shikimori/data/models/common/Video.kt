package com.gmkornilov.shikimori.data.models.common

import kotlinx.serialization.Serializable
import com.gmkornilov.shikimori.domain.models.common.Video as DomainVideo

@Serializable
data class Video(
    val id: Long,
    val url: String,
    val imageUrl: String,
    val playerUrl: String,
    val name: String,
    val kind: String,
    val hosting: String,
)

fun Video.toDomainVideo(): DomainVideo {
    return DomainVideo(id, url, imageUrl, playerUrl, name, kind, hosting)
}