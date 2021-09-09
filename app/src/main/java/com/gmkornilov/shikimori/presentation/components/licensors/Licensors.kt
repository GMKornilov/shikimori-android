package com.gmkornilov.shikimori.presentation.components.licensors

import com.gmkornilov.shikimori.presentation.components.BaseComponent

data class Licensors(
    val key: String,
    val licensors: List<Licensor>,
): BaseComponent {
    override fun id(): Any {
        return key
    }

    override fun content(): Any {
        return this
    }

}
