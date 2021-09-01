package com.gmkornilov.shikimori.di.backstack

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.di.filteredanimespage.FilteredAnimesPageComponent
import com.gmkornilov.shikimori.di.mainpage.MainPageComponent
import dagger.Module
import dagger.Provides

@Module
object BackstackNavigationModule {
    private val cicerone = Cicerone.create()

    @Provides
    @BackstackNavigation
    @BackstackScope
    fun getNavigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    @BackstackNavigation
    @BackstackScope
    fun getRouter(): Router = cicerone.router
}