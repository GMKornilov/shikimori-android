package com.gmkornilov.shikimori.data.retrofit

import com.gmkornilov.shikimori.domain.models.common.*
import com.gmkornilov.shikimori.domain.models.mainpage.AnimePreview
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeRemote {
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
}