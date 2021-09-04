package com.gmkornilov.shikimori.data.models.common

import kotlinx.serialization.Serializable
import com.gmkornilov.shikimori.domain.models.common.Genre as DomainGenre

/**
 * Information about genre
 */
@Serializable
data class Genre(
    /**
     * Genre id
     */
    val id: Long,

    /**
     * Genre's english name
     */
    val name: String,

    /**
     * Genre's russian name
     */
    val russian: String,

    /**
     * Genre's kind (can have "anime" or "manga")
     */
    val kind: String,
)

/**
 * Convert data model of anime genre to domain model
 */
fun Genre.toDomainGenre(): DomainGenre {
    return DomainGenre(id, name, russian, kind)
}