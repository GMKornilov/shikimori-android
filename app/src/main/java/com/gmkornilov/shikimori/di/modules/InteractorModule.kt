package com.gmkornilov.shikimori.di.modules

import com.gmkornilov.shikimori.domain.interactors.SingleUseCase
import com.gmkornilov.shikimori.domain.interactors.filteredanimes.FilteredAnimesInteractor
import com.gmkornilov.shikimori.domain.interactors.filteredanimes.FilteredAnimesInteractorImpl
import com.gmkornilov.shikimori.domain.interactors.mainpage.*
import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
interface InteractorModule {
    @Binds
    fun bindMainPageInteractor(mainPageInteractorImpl: MainPageInteractorImpl): MainPageInteractor

    @Binds
    fun bindFilteredAnimesInteractor(filteredAnimesInteractorImpl: FilteredAnimesInteractorImpl): FilteredAnimesInteractor
}