package com.gmkornilov.shikimori.presentation.components.stat

import com.gmkornilov.shikimori.databinding.StatLayoutBinding
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun statAdapterDelegate() = adapterDelegateViewBinding<Stat, BaseComponent, StatLayoutBinding>(
    { layoutInflater, parent -> StatLayoutBinding.inflate(layoutInflater, parent, false) }
) {
    bind {
        with(binding) {
            progressBar.max = item.maxValue.toFloat()
            progressBar.progress = item.value.toFloat()
            progressBar.labelText = item.value.toString()
            labelText.text = item.key
        }
    }
}