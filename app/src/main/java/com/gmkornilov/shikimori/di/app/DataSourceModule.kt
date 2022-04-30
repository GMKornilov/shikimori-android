package com.gmkornilov.shikimori.di.app

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.gmkornilov.shikimori.BuildConfig
import com.gmkornilov.shikimori.data.retrofit.AnimeRemote
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

@Module
object DataSourceModule {
    @Provides
    @AppScope
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val cache = Cache(context.cacheDir, cacheSize)

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            var isAvailable: Boolean = false
                private set

            override fun onAvailable(network: Network) {
                super.onAvailable(network)

                isAvailable = true
            }

            override fun onUnavailable() {
                super.onUnavailable()

                isAvailable = false
            }
        }
        connectivityManager.registerDefaultNetworkCallback(networkCallback)

        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor {
                val request = it.request().newBuilder()
                    .addHeader("User-Agent", BuildConfig.USER_AGENT)
                    .build()
                it.proceed(request)
            }
            .addInterceptor {
                var request = it.request()

                /*
                *  Leveraging the advantage of using Kotlin,
                *  we initialize the request and change its header depending on whether
                *  the device is connected to Internet or not.
                */
                request = if (networkCallback.isAvailable) {
                    /*
                    *  If there is Internet, get the cache that was stored 120 seconds ago.
                    *  If the cache is older than 120 seconds, then discard it,
                    *  and indicate an error in fetching the response.
                    *  The 'max-age' attribute is responsible for this behavior.
                    */
                    request.newBuilder().addHeader(
                        "Cache-Control",
                        "public, max-age=$cacheStoreOnline"
                    ).build()
                } else {
                    /*
                    *  If there is no Internet, get the cache that was stored 7 days ago.
                    *  If the cache is older than 7 days, then discard it,
                    *  and indicate an error in fetching the response.
                    *  The 'max-stale' attribute is responsible for this behavior.
                    *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                    */
                    request.newBuilder().addHeader(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=$cacheStoreOffline"
                    ).build()
                }
                it.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    private val contentType = "application/json; charset=utf-8".toMediaType()

    /**
     * Defines how much http cache we store(in MB)
     */
    private const val cacheSize = (5 * 1024 * 1024).toLong()

    /**
     * Defines how long we store cache when online(in seconds)
     */
    private const val cacheStoreOnline = 120

    /**
     * Defines hwo long we store cache when offline(in seconds)
     */
    private const val cacheStoreOffline = 60 * 60 * 24 * 7

    @Provides
    @AppScope
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @AppScope
    fun provideAnimeRemote(retrofit: Retrofit): AnimeRemote {
        return retrofit.create(AnimeRemote::class.java)
    }
}