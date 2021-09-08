package com.gmkornilov.shikimori.presentation.components.rating

import com.gmkornilov.shikimori.databinding.ItemRatingBinding
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun ratingAdapterDelegate() = adapterDelegateViewBinding<Rating, BaseComponent, ItemRatingBinding>(
    {layoutInflater, parent -> ItemRatingBinding.inflate(layoutInflater, parent, false) }
) {
    bind {
        with (binding) {
            ratingBar.rating = item.value / 2
            ratingText.text = item.value.toString()
            ratingLabel.text = item.rate
        }
    }
}