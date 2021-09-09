package com.gmkornilov.shikimori.presentation.components.licensors

import com.gmkornilov.shikimori.presentation.components.BaseComponent

data class Licensor(
    val name: String,
): BaseComponent {
    override fun id(): Any {
        return this
    }

    override fun content(): Any {
        return this
    }
}