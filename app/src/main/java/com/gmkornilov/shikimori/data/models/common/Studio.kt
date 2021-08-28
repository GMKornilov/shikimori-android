package com.gmkornilov.shikimori.data.models.common

import kotlinx.serialization.Serializable
import com.gmkornilov.shikimori.domain.models.common.Studio as DomainStudio

@Serializable
data class Studio(
    val id: Long,
    val name: String,
    val filteredName: String,
    val real: Boolean,
    val imageUrl: String,
)

fun Studio.toDomainStudio(): DomainStudio {
    return DomainStudio(id, name, filteredName, real, imageUrl)
}