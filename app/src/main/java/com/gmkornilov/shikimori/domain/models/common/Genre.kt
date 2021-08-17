package com.gmkornilov.shikimori.domain.models.common

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: Long,
    val name: String,
    val russian: String,
    val kind: String,
)
