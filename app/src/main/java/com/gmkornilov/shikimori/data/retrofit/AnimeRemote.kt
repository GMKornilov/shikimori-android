package com.gmkornilov.shikimori.data.retrofit

import com.gmkornilov.shikimori.data.models.common.*
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeRemote {
    @ExperimentalSerializationApi
    @GET("/api/animes")
    fun getAnimes(
        @Query("page") page: Int?,
        @Query("limit") limit: Int?,
        @Query("order") order: AnimeOrder?,
        @Query("kind") kind: AnimeKind?,
        @Query("status") status: AnimeStatus?,
        @Query("season") seasons: String?,
        @Query("score") minimumScore: Float?,
        @Query("duration") duration: AnimeDuration?,
        @Query("rating") rating: AnimeRating?,
        @Query("genre") genreIdsString: String?,
        @Query("studio") studioIdsString: String?,
        @Query("franchise") franchisesString: String?,
        @Query("censored") censored: Boolean?,
        // mylist
        @Query("ids") idsString: String?,
        @Query("exclude_ids") excludeIdsString: String?,
        @Query("search") searchString: String?
    ): Call<List<AnimePreview>>

    @ExperimentalSerializationApi
    @GET("api/animes/{id}")
    fun getAnime(@Path("id") id: Long): Call<AnimeInfo>
}