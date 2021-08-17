package com.gmkornilov.shikimori.di.modules

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CiceroneModule {
    private val cicerone = Cicerone.create()

    @Provides
    fun getNavigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    fun getRouter(): Router = cicerone.router
}