package com.gmkornilov.shikimori.presentation.components

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

/**
 * Marker parent interface for delegate adapter
 */
interface BaseComponent {
    fun id(): Any

    fun content(): Any

    companion object DIFF : DiffUtil.ItemCallback<BaseComponent>() {
        override fun areItemsTheSame(
            oldItem: BaseComponent,
            newItem: BaseComponent
        ): Boolean {
            return oldItem::class == newItem::class && oldItem.id() == newItem.id()
        }

        // we suppress that lint because we assume that areItemsTheSame(oldItem, newItem) is true
        // thus their classes are equal
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: BaseComponent,
            newItem: BaseComponent
        ): Boolean {
            return oldItem.content() == newItem.content()
        }

    }
}