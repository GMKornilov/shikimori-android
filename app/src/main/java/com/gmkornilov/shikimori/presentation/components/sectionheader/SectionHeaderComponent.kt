package com.gmkornilov.shikimori.presentation.components.sectionheader

import com.gmkornilov.shikimori.presentation.components.BaseComponent

data class SectionHeaderComponent(
    val sectionTitle: String,
) : BaseComponent {
    override fun id(): Any {
        return this
    }

    override fun content(): Any {
        return this
    }

}
