package com.gmkornilov.shikimori.di.app

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.di.filteredanimespage.FilteredAnimesPageComponent
import com.gmkornilov.shikimori.di.mainpage.MainPageComponent
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(subcomponents = [MainPageComponent::class, FilteredAnimesPageComponent::class])
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