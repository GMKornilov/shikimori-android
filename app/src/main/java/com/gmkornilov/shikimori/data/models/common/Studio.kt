package com.gmkornilov.shikimori.data.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.gmkornilov.shikimori.domain.models.common.Studio as DomainStudio

/**
 * Information about anime studio
 */
@Serializable
data class Studio(
    val id: Long,
    val name: String,
    @SerialName("filtered_name") val filteredName: String,
    val real: Boolean,
    @SerialName("image") val imageUrl: String?,
)

/**
 * Convert data model of anime studio to domain model
 */
fun Studio.toDomainStudio(): DomainStudio {
    return DomainStudio(id, name, filteredName, real, imageUrl)
}