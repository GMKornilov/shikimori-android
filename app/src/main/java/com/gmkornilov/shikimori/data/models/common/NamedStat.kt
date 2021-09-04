package com.gmkornilov.shikimori.data.models.common

import kotlinx.serialization.Serializable
import com.gmkornilov.shikimori.domain.models.common.NamedStat as DomainNamedStat

/**
 * Data class for key-value values
 */
@Serializable
data class NamedStat(
    val name: String,
    val value: Int,
)

fun NamedStat.toDomainNamedStat(): DomainNamedStat {
    return DomainNamedStat(name, value)
}