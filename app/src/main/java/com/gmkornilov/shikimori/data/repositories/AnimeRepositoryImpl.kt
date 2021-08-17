package com.gmkornilov.shikimori.data.repositories

import com.gmkornilov.shikimori.data.retrofit.AnimeRemote
import com.gmkornilov.shikimori.domain.error.BadRequestException
import com.gmkornilov.shikimori.domain.error.NotFoundException
import com.gmkornilov.shikimori.domain.error.ServerException
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimeInfo
import com.gmkornilov.shikimori.domain.repositories.AnimeRepository
import javax.inject.Inject

class AnimeRepositoryImpl @Inject constructor(
    private val animeRemote: AnimeRemote,
) : AnimeRepository {
    override fun animesByFilter(filter: AnimeFilter): List<AnimeInfo> {
        val response = animeRemote.getAnimes(
            filter.page,
            filter.limit,
            filter.order,
            filter.kind,
            filter.status,
            filter.season,
            filter.minimalScore,
            filter.duration,
            filter.rating,
            filter.genreIds?.joinToString(",") { it.toString() },
            filter.studioIds?.joinToString(",") { it.toString() },
            filter.franchises?.joinToString { it },
            filter.censored,
            filter.ids?.joinToString(",") { it.toString() },
            filter.excludeIds?.joinToString(",") { it.toString() },
            filter.searchString,
        ).execute()

        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return body
            } else {
                throw Exception()
            }
        }
        throw when (response.code()) {
            UNPROCESSABLE_ENTITY -> BadRequestException()
            NOT_FOUND -> NotFoundException()
            in SERVER_ERROR..SERVER_ERROR+100 -> ServerException()
            else -> Exception()
        }

    }

    companion object {
        private const val UNPROCESSABLE_ENTITY = 422
        private const val NOT_FOUND = 404
        private const val SERVER_ERROR = 500
    }
}