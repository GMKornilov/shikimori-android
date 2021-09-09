package com.gmkornilov.shikimori.presentation.components.licensors

import com.gmkornilov.shikimori.databinding.ItemKeyValueListBinding
import com.gmkornilov.shikimori.databinding.ItemListValueBinding
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexboxLayoutManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun licensorsAdapterDelegate() =
    adapterDelegateViewBinding<Licensors, BaseComponent, ItemKeyValueListBinding>(
        { layoutInflater, parent -> ItemKeyValueListBinding.inflate(layoutInflater, parent, false) }
    ) {
        val adapter = AsyncListDifferDelegationAdapter(
            BaseComponent,
            licensorAdapterDelegate(),
        )
        binding.valueList.adapter = adapter
        binding.valueList.layoutManager = object : FlexboxLayoutManager(binding.valueList.context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }.apply {
            alignItems = AlignItems.BASELINE
        }

        bind {
            binding.keyText.text = item.key
            adapter.items = item.licensors
        }
    }


fun licensorAdapterDelegate() = adapterDelegateViewBinding<Licensor, BaseComponent, ItemListValueBinding>(
    {layoutInflater, parent -> ItemListValueBinding.inflate(layoutInflater, parent, false) }
) {
    bind {
        binding.genreChip.text = item.name
    }
}