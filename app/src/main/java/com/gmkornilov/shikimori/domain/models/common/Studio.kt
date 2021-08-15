package com.gmkornilov.shikimori.domain.models.common

data class Studio(
    val id: Long,
    val name: String,
    val filteredName: String,
    val real: Boolean,
    val imageUrl: String,
)