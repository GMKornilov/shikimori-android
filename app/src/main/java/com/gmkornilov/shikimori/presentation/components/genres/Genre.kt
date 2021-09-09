package com.gmkornilov.shikimori.presentation.components.genres

import com.gmkornilov.shikimori.presentation.components.BaseComponent

data class Genre(
    val id: Long,
    val genreName: String,
) :BaseComponent {
    override fun id(): Any {
        return id
    }

    override fun content(): Any {
        return this
    }
}
