package com.gmkornilov.shikimori.di.app

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides

@Module
object NetworkModule {
    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}