package com.gmkornilov.shikimori.di.animepage

import com.gmkornilov.shikimori.domain.interactors.animepage.AnimePageInteractor
import com.gmkornilov.shikimori.domain.interactors.animepage.AnimePageInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface AnimePageModule {
    @Binds
    fun bindAnimePageInteractor(animePageInteractorImpl: AnimePageInteractorImpl): AnimePageInteractor
}