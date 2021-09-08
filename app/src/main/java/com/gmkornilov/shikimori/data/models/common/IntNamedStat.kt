package com.gmkornilov.shikimori.data.models.common

import kotlinx.serialization.Serializable
import com.gmkornilov.shikimori.domain.models.common.NamedStat as DomainNamedStat

@Serializable
data class IntNamedStat(
    val name: Int,
    val value: Int,
)

fun IntNamedStat.toDomainNamedStat(): DomainNamedStat {
    return DomainNamedStat(name.toString(), value)
}
