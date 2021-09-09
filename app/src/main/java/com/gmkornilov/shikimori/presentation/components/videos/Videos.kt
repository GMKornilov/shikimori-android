package com.gmkornilov.shikimori.presentation.components.videos

import com.gmkornilov.shikimori.presentation.components.BaseComponent

data class Videos(
    val videos: List<Video>
) : BaseComponent {
    override fun id(): Any {
        return this
    }

    override fun content(): Any {
        return this
    }
}
