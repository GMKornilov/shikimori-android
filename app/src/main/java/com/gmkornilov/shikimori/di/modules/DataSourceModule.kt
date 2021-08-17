package com.gmkornilov.shikimori.di.modules

import com.gmkornilov.shikimori.BuildConfig
import com.gmkornilov.shikimori.data.retrofit.AnimeRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object DataSourceModule {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request().newBuilder()
                .addHeader("User-Agent", BuildConfig.USER_AGENT)
                .build()
            it.proceed(request)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .build()

    @Provides
    fun provideRetrofit(): AnimeRemote {
        return retrofit.create(AnimeRemote::class.java)
    }
}