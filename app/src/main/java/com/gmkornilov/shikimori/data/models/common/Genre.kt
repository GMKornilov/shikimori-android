package com.gmkornilov.shikimori.data.models.common

import kotlinx.serialization.Serializable
import com.gmkornilov.shikimori.domain.models.common.Genre as DomainGenre

@Serializable
data class Genre(
    val id: Long,
    val name: String,
    val russian: String,
    val kind: String,
)


fun Genre.toDomainGenre(): DomainGenre {
    return DomainGenre(id, name, russian, kind)
}