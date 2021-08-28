package com.gmkornilov.shikimori.data.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.gmkornilov.shikimori.domain.models.common.ImageInfo as DomainImageInfo

@Serializable
data class ImageInfo(
    @SerialName("original") val urlOriginal: String,
    @SerialName("preview") val urlPreview: String,
    @SerialName("x96") val urlX96: String,
    @SerialName("x48") val urlX48: String,
)

fun ImageInfo.toDomainImageInfo(): DomainImageInfo {
    return DomainImageInfo(urlOriginal, urlPreview, urlX96, urlX48)
}