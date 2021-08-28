package com.gmkornilov.shikimori.data.models.common

import kotlinx.serialization.Serializable
import com.gmkornilov.shikimori.domain.models.common.Screenshot as DomainScreenshot

@Serializable
data class Screenshot(
    val originalUrl: String,
    val previewUrl: String,
)

fun Screenshot.toDomainScreenshot(): DomainScreenshot {
    return DomainScreenshot(originalUrl, previewUrl)
}