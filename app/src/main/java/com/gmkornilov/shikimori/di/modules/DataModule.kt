package com.gmkornilov.shikimori.di.modules

import com.gmkornilov.shikimori.data.repositories.AnimeRepositoryImpl
import com.gmkornilov.shikimori.domain.models.common.AnimeInfo
import com.gmkornilov.shikimori.domain.models.mainpage.AnimePreview
import com.gmkornilov.shikimori.domain.models.mapper.AnimePreviewsMapper
import com.gmkornilov.shikimori.domain.models.mapper.TypeDataMapper
import com.gmkornilov.shikimori.domain.repositories.AnimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DataModule {
    @Binds
    fun bindAnimeRepositoryImpl(animeRepositoryImpl: AnimeRepositoryImpl): AnimeRepository

    @Binds
    fun bindAnimePreviewsMapper(animePreviewsMapper: AnimePreviewsMapper): TypeDataMapper<AnimeInfo, AnimePreview>
}