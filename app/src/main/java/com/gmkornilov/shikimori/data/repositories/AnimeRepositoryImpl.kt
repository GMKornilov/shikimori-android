package com.gmkornilov.shikimori.data.repositories

import androidx.annotation.WorkerThread
import com.gmkornilov.shikimori.BuildConfig
import com.gmkornilov.shikimori.data.models.common.*
import com.gmkornilov.shikimori.data.retrofit.AnimeRemote
import com.gmkornilov.shikimori.domain.error.BadRequestException
import com.gmkornilov.shikimori.domain.error.NotFoundException
import com.gmkornilov.shikimori.domain.error.ServerException
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimeInfo
import com.gmkornilov.shikimori.domain.models.common.AnimePreview as DomainAnimePreview
import com.gmkornilov.shikimori.domain.repositories.AnimeRepository
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeRemote: AnimeRemote,
) : AnimeRepository {
    @ExperimentalSerializationApi
    @WorkerThread
    override fun animesByFilter(filter: AnimeFilter, needsRefresh: Boolean): List<DomainAnimePreview> {
        val dataFilter = filter.toDataAnimeFilter()
        val response = animeRemote.getAnimes(
            dataFilter.page,
            dataFilter.limit,
            dataFilter.order,
            dataFilter.kind,
            dataFilter.status,
            dataFilter.season,
            dataFilter.minimalScore,
            dataFilter.duration,
            dataFilter.rating,
            dataFilter.genreIds?.joinToString(",") { it.toString() },
            dataFilter.studioIds?.joinToString(",") { it.toString() },
            dataFilter.franchises?.joinToString { it },
            dataFilter.censored,
            dataFilter.ids?.joinToString(",") { it.toString() },
            dataFilter.excludeIds?.joinToString(",") { it.toString() },
            dataFilter.searchString,
        ).execute()

        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return body.map {
                    mapAnimePreview(it).toDomainAnimePreview()
                }
            } else {
                throw Exception()
            }
        }
        throw when (response.code()) {
            UNPROCESSABLE_ENTITY -> BadRequestException()
            NOT_FOUND -> NotFoundException()
            in SERVER_ERROR..SERVER_ERROR + 100 -> ServerException()
            else -> Exception()
        }
    }

    @ExperimentalSerializationApi
    @WorkerThread
    override fun animeById(id: Long): AnimeInfo {
        val response = animeRemote.getAnime(id).execute()

        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return body.toDomainAnimeInfo()
            } else {
                throw Exception()
            }
        }
        throw when (response.code()) {
            UNPROCESSABLE_ENTITY -> BadRequestException()
            NOT_FOUND -> NotFoundException()
            in SERVER_ERROR..SERVER_ERROR + 100 -> ServerException()
            else -> Exception()
        }
    }

    /**
     * Remote API returns relative path for image urls, so this method fixes it
     */
    @ExperimentalSerializationApi
    private fun mapAnimePreview(animePreview: AnimePreview): AnimePreview {
        return AnimePreview(
            animePreview.id,
            animePreview.name,
            animePreview.russianName,
            ImageInfo(
                BuildConfig.BASE_URL + animePreview.imageInfo.urlOriginal,
                BuildConfig.BASE_URL + animePreview.imageInfo.urlPreview,
                BuildConfig.BASE_URL + animePreview.imageInfo.urlX48,
                BuildConfig.BASE_URL + animePreview.imageInfo.urlX96,
            ),
            animePreview.url,
            animePreview.kind,
            animePreview.score,
            animePreview.status,
            animePreview.episodes,
            animePreview.episodesAired,
            animePreview.airedOn,
            animePreview.releasedOn
        )
    }

    companion object {
        private const val UNPROCESSABLE_ENTITY = 422
        private const val NOT_FOUND = 404
        private const val SERVER_ERROR = 500
    }
}