package com.gmkornilov.shikimori.domain.models.common

import kotlinx.serialization.Serializable

@Serializable
data class Screenshot(
    val originalUrl: String,
    val previewUrl: String,
)