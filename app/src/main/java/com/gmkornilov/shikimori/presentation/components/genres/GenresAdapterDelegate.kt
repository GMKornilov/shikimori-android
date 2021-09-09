package com.gmkornilov.shikimori.presentation.components.genres

import androidx.constraintlayout.widget.ConstraintLayout
import com.gmkornilov.shikimori.databinding.ItemKeyValueListBinding
import com.gmkornilov.shikimori.databinding.ItemListValueBinding
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.google.android.flexbox.FlexboxLayoutManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun genresAdapterDelegate() =
    adapterDelegateViewBinding<Genres, BaseComponent, ItemKeyValueListBinding>(
        { layoutInflater, parent -> ItemKeyValueListBinding.inflate(layoutInflater, parent, false) }
    ) {
        val adapter = AsyncListDifferDelegationAdapter(
            BaseComponent,
            genreAdapterDelegate(),
        )
        binding.valueList.adapter = adapter

        binding.valueList.layoutManager = object : FlexboxLayoutManager(binding.valueList.context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        bind {
            binding.keyText.text = item.key
            adapter.items = item.genres
        }
    }


fun genreAdapterDelegate() = adapterDelegateViewBinding<Genre, BaseComponent, ItemListValueBinding>(
    {layoutInflater, parent -> ItemListValueBinding.inflate(layoutInflater, parent, false) }
) {
    bind {
        binding.genreChip.text = item.genreName
    }
}