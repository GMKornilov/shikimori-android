package com.gmkornilov.shikimori.domain.models.common

/**
 * Information about video
 */
data class Video(
    val id: Long,
    val url: String,
    val imageUrl: String,
    val playerUrl: String,
    val name: String,
    val kind: String,
    val hosting: String,
)
