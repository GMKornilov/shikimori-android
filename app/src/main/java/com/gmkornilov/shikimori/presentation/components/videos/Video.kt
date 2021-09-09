package com.gmkornilov.shikimori.presentation.components.videos

import com.gmkornilov.shikimori.presentation.components.BaseComponent

data class Video(
    val thumbnailUrl: String,
    val videoUrl: String,
): BaseComponent {
    override fun id(): Any {
        return this
    }

    override fun content(): Any {
        return this
    }
}