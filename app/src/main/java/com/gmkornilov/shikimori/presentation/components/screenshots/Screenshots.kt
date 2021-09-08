package com.gmkornilov.shikimori.presentation.components.screenshots

import com.gmkornilov.shikimori.presentation.components.BaseComponent

data class Screenshots(
    val urls: List<String>
) : BaseComponent {
    override fun id(): Any {
        return this
    }

    override fun content(): Any {
        return this
    }
}
