package com.gmkornilov.shikimori.domain.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageInfo(
    @SerialName("original") val urlOriginal: String,
    @SerialName("preview") val urlPreview: String,
    @SerialName("x96") val urlX96: String,
    @SerialName("x48") val urlX48: String,
)