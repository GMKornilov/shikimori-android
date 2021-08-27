package com.gmkornilov.shikimori.di.app

import com.gmkornilov.shikimori.BuildConfig
import com.gmkornilov.shikimori.data.retrofit.AnimeRemote
import com.gmkornilov.shikimori.di.filteredanimespage.FilteredAnimesPageComponent
import com.gmkornilov.shikimori.di.mainpage.MainPageComponent
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module(subcomponents = [MainPageComponent::class, FilteredAnimesPageComponent::class])
object DataSourceModule {
    private val contentType = "application/json; charset=utf-8".toMediaType()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("User-Agent", BuildConfig.USER_AGENT)
                .build()
            it.proceed(request)
        }
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()

    @Provides
    @AppScope
    fun provideRetrofit(): AnimeRemote {
        return retrofit.create(AnimeRemote::class.java)
    }
}