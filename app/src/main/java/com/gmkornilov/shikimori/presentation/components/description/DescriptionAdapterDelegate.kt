package com.gmkornilov.shikimori.presentation.components.description

import com.gmkornilov.shikimori.databinding.ItemDescriptionBinding
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun descriptionAdapterDelegate() =
    adapterDelegateViewBinding<DescriptionComponent, BaseComponent, ItemDescriptionBinding>(
        { layoutInflater, parent -> ItemDescriptionBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            binding.descriptionText.text = item.description
        }
    }