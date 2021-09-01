package com.gmkornilov.shikimori.di.app

import com.gmkornilov.shikimori.data.repositories.AnimeRepositoryImpl
import com.gmkornilov.shikimori.domain.repositories.AnimeRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {
    @Binds
    @AppScope
    fun bindAnimeRepositoryImpl(animeRepositoryImpl: AnimeRepositoryImpl): AnimeRepository
}