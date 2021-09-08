package com.gmkornilov.shikimori.presentation.components.stat

import com.gmkornilov.shikimori.domain.models.common.NamedStat
import com.gmkornilov.shikimori.presentation.components.BaseComponent

data class Stat(
    val key: String,
    val value: Int,
    val maxValue: Int
) : BaseComponent {
    override fun id(): Any {
        return key
    }

    override fun content(): Any {
        return this
    }
}

fun NamedStat.toStat(maxValue: Int): Stat {
    return Stat(this.name, this.value, maxValue)
}
