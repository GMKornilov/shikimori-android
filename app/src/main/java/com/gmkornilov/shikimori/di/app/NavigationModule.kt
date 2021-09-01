package com.gmkornilov.shikimori.di.app

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
object NavigationModule {
    private val cicerone = Cicerone.create()

    @Provides
    @AppScope
    @GlobalNavigation
    fun getNavigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    @AppScope
    @GlobalNavigation
    fun getRouter(): Router = cicerone.router
}