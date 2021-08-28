package com.gmkornilov.shikimori.presentation.items.animepreview

import androidx.annotation.StringRes
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import com.gmkornilov.shikimori.R
import java.text.SimpleDateFormat
import java.util.*

import com.gmkornilov.shikimori.domain.models.common.AnimePreview as DomainAnimePreview
import com.gmkornilov.shikimori.domain.models.common.AnimeKind as DomainAnimeKind

data class AnimePreview(
    val id: Long,
    val thumbnailImageUrl: String,
    val titleText: String,
    val kind: AnimeKind,
    val airedOnYearString: String?,
) {
    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<AnimePreview>() {
            override fun areItemsTheSame(oldItem: AnimePreview, newItem: AnimePreview): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: AnimePreview, newItem: AnimePreview): Boolean {
                return oldItem == newItem
            }
        }
        val config by lazy(LazyThreadSafetyMode.NONE) {
            AsyncDifferConfig.Builder(DIFF).build()
        }
    }
}

fun DomainAnimePreview.toPresentationAnimePreview(): AnimePreview {
    val dateFormat = SimpleDateFormat("yyyy", Locale.ENGLISH)

    val year = if (this.airedOn != null) {
        dateFormat.format(airedOn)
    } else {
        null
    }

    return AnimePreview(
        this.id,
        this.imageInfo.urlPreview,
        this.name,
        this.kind.toPresentationAnimeKind(),
        year,
    )
}

enum class AnimeKind(@StringRes val titleResourceId: Int) {
    TV(R.string.tv),
    MOVIE(R.string.movie),
    OVA(R.string.ova),
    ONA(R.string.ona),
    SPECIAL(R.string.special),
    MUSIC(R.string.music),
    TV_13(R.string.tv),
    TV_24(R.string.tv),
    TV_48(R.string.tv),
}

fun DomainAnimeKind.toPresentationAnimeKind(): AnimeKind {
    return AnimeKind.valueOf(this.toString())
}

fun AnimeKind.toDomainAnimeKind(): DomainAnimeKind {
    return DomainAnimeKind.valueOf(this.toString())
}

