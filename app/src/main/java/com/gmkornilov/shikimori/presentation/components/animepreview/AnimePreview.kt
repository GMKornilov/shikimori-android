package com.gmkornilov.shikimori.presentation.components.animepreview

import android.os.Parcelable
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.gmkornilov.shikimori.presentation.models.common.AnimeKind
import com.gmkornilov.shikimori.presentation.models.common.toPresentationAnimeKind
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

import com.gmkornilov.shikimori.domain.models.common.AnimePreview as DomainAnimePreview

@Parcelize
data class AnimePreview(
    val id: Long,
    val thumbnailImageUrl: String,
    val titleText: String,
    val kind: AnimeKind,
    val airedOnYearString: String?,
) : Parcelable, BaseComponent {
    override fun id(): Any {
        return id
    }

    override fun content(): Any {
        return this
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