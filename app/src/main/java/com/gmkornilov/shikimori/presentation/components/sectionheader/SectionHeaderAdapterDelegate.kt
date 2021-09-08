package com.gmkornilov.shikimori.presentation.components.sectionheader

import com.gmkornilov.shikimori.databinding.ItemSectionHeaderBinding
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun sectionHeaderAdapterDelegate() =
    adapterDelegateViewBinding<SectionHeaderComponent, BaseComponent, ItemSectionHeaderBinding>(
        { layoutInflater, parent ->
            ItemSectionHeaderBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        }
    ) {
        bind {
            binding.sectionHeader.text = item.sectionTitle
        }
    }