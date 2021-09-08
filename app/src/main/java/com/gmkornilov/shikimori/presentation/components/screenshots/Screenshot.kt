package com.gmkornilov.shikimori.presentation.components.screenshots

import com.gmkornilov.shikimori.presentation.components.BaseComponent

data class Screenshot(
    val url: String
): BaseComponent {
    override fun id(): Any {
        return this
    }

    override fun content(): Any {
        return this
    }

}
