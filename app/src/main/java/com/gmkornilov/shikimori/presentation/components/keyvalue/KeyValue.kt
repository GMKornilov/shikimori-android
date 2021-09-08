package com.gmkornilov.shikimori.presentation.components.keyvalue

import com.gmkornilov.shikimori.presentation.components.BaseComponent

data class KeyValue(
    val key: String,
    val value: String,
) : BaseComponent {
    override fun id(): Any {
        return key
    }

    override fun content(): Any {
        return this
    }

}
