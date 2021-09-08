package com.gmkornilov.shikimori.presentation.components.description

import com.gmkornilov.shikimori.presentation.components.BaseComponent

data class DescriptionComponent(
    val description: String
) : BaseComponent {
    override fun id(): Any {
        return this
    }

    override fun content(): Any {
        return this
    }
}
