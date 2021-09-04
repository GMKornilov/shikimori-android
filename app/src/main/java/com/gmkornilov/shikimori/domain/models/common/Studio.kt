package com.gmkornilov.shikimori.domain.models.common

/**
 * Information about anime studio
 */
data class Studio(
    val id: Long,
    val name: String,
    val filteredName: String,
    val real: Boolean,
    val imageUrl: String,
)