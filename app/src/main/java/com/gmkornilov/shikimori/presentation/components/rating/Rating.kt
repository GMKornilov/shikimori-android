package com.gmkornilov.shikimori.presentation.components.rating

import com.gmkornilov.shikimori.presentation.components.BaseComponent

data class Rating(
    val value: Float,
    val rate: String,
): BaseComponent {
    override fun id(): Any {
        return this
    }

    override fun content(): Any {
        return this
    }
}