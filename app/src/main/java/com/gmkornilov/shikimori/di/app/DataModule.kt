package com.gmkornilov.shikimori.di.app

import com.gmkornilov.shikimori.data.repositories.AnimeRepositoryImpl
import com.gmkornilov.shikimori.di.filteredanimespage.FilteredAnimesPageComponent
import com.gmkornilov.shikimori.di.mainpage.MainPageComponent
import com.gmkornilov.shikimori.domain.repositories.AnimeRepository
import dagger.Binds
import dagger.Module

@Module(subcomponents = [MainPageComponent::class, FilteredAnimesPageComponent::class])
interface DataModule {
    @Binds
    @AppScope
    fun bindAnimeRepositoryImpl(animeRepositoryImpl: AnimeRepositoryImpl): AnimeRepository
}