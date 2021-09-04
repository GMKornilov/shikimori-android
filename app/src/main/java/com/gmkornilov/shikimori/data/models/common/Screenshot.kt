package com.gmkornilov.shikimori.data.models.common

import kotlinx.serialization.Serializable
import com.gmkornilov.shikimori.domain.models.common.Screenshot as DomainScreenshot

/**
 * Information about screenshot
 */
@Serializable
data class Screenshot(
    val originalUrl: String,
    val previewUrl: String,
)

/**
 * Convert data model of screenshot to domain model
 */
fun Screenshot.toDomainScreenshot(): DomainScreenshot {
    return DomainScreenshot(originalUrl, previewUrl)
}