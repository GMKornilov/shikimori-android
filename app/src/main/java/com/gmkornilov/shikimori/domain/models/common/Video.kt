package com.gmkornilov.shikimori.domain.models.common

import kotlinx.serialization.Serializable

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
