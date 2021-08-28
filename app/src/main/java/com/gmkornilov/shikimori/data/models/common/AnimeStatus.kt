package com.gmkornilov.shikimori.data.models.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.gmkornilov.shikimori.domain.models.common.AnimeStatus as DomainAnimeStatus

@Serializable
enum class AnimeStatus {
    @SerialName("anons") ANONS,
    @SerialName("ongoing") ONGOING,
    @SerialName("released") RELEASED;

    override fun toString(): String {
        return super.toString().lowercase()
    }
}

fun AnimeStatus.toDomainAnimeStatus(): DomainAnimeStatus {
    return DomainAnimeStatus.valueOf(this.toString().uppercase())
}

fun DomainAnimeStatus.toDataAnimeStatus(): AnimeStatus {
    return AnimeStatus.valueOf(this.toString())
}