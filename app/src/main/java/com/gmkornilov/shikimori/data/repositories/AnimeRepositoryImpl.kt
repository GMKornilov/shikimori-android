package com.gmkornilov.shikimori.data.repositories

import androidx.annotation.WorkerThread
import com.gmkornilov.shikimori.BuildConfig
import com.gmkornilov.shikimori.data.http.RequestResult
import com.gmkornilov.shikimori.data.http.mapSuccessful
import com.gmkornilov.shikimori.data.http.responseToRequestResult
import com.gmkornilov.shikimori.data.models.common.*
import com.gmkornilov.shikimori.data.retrofit.AnimeRemote
import com.gmkornilov.shikimori.domain.error.BadRequestException
import com.gmkornilov.shikimori.domain.error.NotFoundException
import com.gmkornilov.shikimori.domain.error.ServerException
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimeInfo as DomainAnimeInfo
import com.gmkornilov.shikimori.domain.models.common.AnimePreview as DomainAnimePreview
import com.gmkornilov.shikimori.domain.repositories.AnimeRepository
import io.reactivex.rxjava3.core.Single
import kotlinx.serialization.ExperimentalSerializationApi
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeRemote: AnimeRemote,
) : AnimeRepository {
    @ExperimentalSerializationApi
    @WorkerThread
    override fun animesByFilter(
        filter: AnimeFilter,
        needsRefresh: Boolean
    ): Single<RequestResult<List<DomainAnimePreview>>> {
        val dataFilter = filter.toDataAnimeFilter()
        return animeRemote.getAnimes(
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
        )
            .responseToRequestResult()
            .mapSuccessful { list -> list.map{ mapAnimePreview(it).toDomainAnimePreview() } }
    }

    @ExperimentalSerializationApi
    @WorkerThread
    override fun animeById(id: Long): Single<RequestResult<DomainAnimeInfo>> {
        return animeRemote.getAnime(id)
            .responseToRequestResult()
            .mapSuccessful { mapAnimeInfo(it).toDomainAnimeInfo() }
    }

    /**
     * Remote API returns relative path for image urls, so this method fixes it
     */
    @ExperimentalSerializationApi
    private fun mapAnimePreview(animePreview: AnimePreview): AnimePreview {
        return animePreview.copy(
            // API always gives relational paths to its inner resources,
            // so we should replace it with absolute path
            imageInfo = animePreview.imageInfo.copy(
                urlOriginal = BuildConfig.BASE_URL + animePreview.imageInfo.urlOriginal,
                urlPreview = BuildConfig.BASE_URL + animePreview.imageInfo.urlPreview,
                urlX96 = BuildConfig.BASE_URL + animePreview.imageInfo.urlX48,
                urlX48 = BuildConfig.BASE_URL + animePreview.imageInfo.urlX96,
            )
        )
    }

    @ExperimentalSerializationApi
    private fun mapAnimeInfo(animeInfo: AnimeInfo): AnimeInfo {
        return animeInfo.copy(
            screenshots = animeInfo.screenshots?.map {
                it.copy(
                    originalUrl = BuildConfig.BASE_URL + it.originalUrl,
                    previewUrl = BuildConfig.BASE_URL + it.previewUrl,
                )
            },
            videos = animeInfo.videos?.map {
                // API sometimes gives thumbnail urls with http instead of https as a protocol,
                // and we should replace it because
                // android doesn't allow cleartext traffic to random resources
                val newUrl = if (!it.imageUrl.contains("https")) {
                    it.imageUrl.replace("http", "https")
                } else {
                    it.imageUrl
                }
                it.copy(
                    imageUrl = newUrl,
                )
            }
        )
    }

    companion object {
        private const val UNPROCESSABLE_ENTITY = 422
        private const val NOT_FOUND = 404
        private const val SERVER_ERROR = 500
    }
}