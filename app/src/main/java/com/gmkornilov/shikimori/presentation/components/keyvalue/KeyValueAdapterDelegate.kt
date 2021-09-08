package com.gmkornilov.shikimori.presentation.components.keyvalue

import com.gmkornilov.shikimori.databinding.ItemKeyValueBinding
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun keyValueAdapterDelegate() =
    adapterDelegateViewBinding<KeyValue, BaseComponent, ItemKeyValueBinding>(
        { layoutInflater, parent -> ItemKeyValueBinding.inflate(layoutInflater, parent, false) }
    ) {
        bind {
            binding.keyText.text = item.key
            binding.valueText.text = item.value
        }
    }