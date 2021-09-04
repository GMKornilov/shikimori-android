package com.gmkornilov.shikimori.domain.models.common

/**
 * Information about image
 */
data class ImageInfo(
    val urlOriginal: String,
    val urlPreview: String,
    val urlX96: String,
    val urlX48: String,
)