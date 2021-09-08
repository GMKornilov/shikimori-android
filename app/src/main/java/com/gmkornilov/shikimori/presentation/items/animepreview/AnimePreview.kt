package com.gmkornilov.shikimori.presentation.items.animepreview

import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import com.gmkornilov.shikimori.R
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

import com.gmkornilov.shikimori.domain.models.common.AnimePreview as DomainAnimePreview
import com.gmkornilov.shikimori.domain.models.common.AnimeKind as DomainAnimeKind

@Parcelize
data class AnimePreview(
    val id: Long,
    val thumbnailImageUrl: String,
    val titleText: String,
    val kind: AnimeKind,
    val airedOnYearString: String?,
) : Parcelable {
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
        this.imageInfo.urlOriginal,
        this.russianName,
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
    UNKNOWN(R.string.unknown)
}

fun DomainAnimeKind?.toPresentationAnimeKind(): AnimeKind {
    return if (this == null) {
        AnimeKind.UNKNOWN
    } else {
        AnimeKind.valueOf(this.toString())
    }
}

fun AnimeKind.toDomainAnimeKind(): DomainAnimeKind? {
    return if (this == AnimeKind.UNKNOWN) {
        null
    } else {
        DomainAnimeKind.valueOf(this.toString())
    }
}

