package com.gmkornilov.shikimori.presentation.components.genres

import com.gmkornilov.shikimori.presentation.components.BaseComponent

data class Genres(
    val key: String,
    val genres: List<Genre>
): BaseComponent {
    override fun id(): Any {
        return key
    }

    override fun content(): Any {
        return this
    }
}
