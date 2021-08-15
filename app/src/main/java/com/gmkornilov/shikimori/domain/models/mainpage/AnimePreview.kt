package com.gmkornilov.shikimori.domain.models.mainpage

data class AnimePreview(
    val id: Long,
    val thumbnailUrl: String,
    val title: String,
    val studio: String?,
)