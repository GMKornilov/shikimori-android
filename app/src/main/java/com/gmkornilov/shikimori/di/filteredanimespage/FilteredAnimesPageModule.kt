package com.gmkornilov.shikimori.di.filteredanimespage

import com.gmkornilov.shikimori.domain.interactors.filteredanimes.FilteredAnimesInteractor
import com.gmkornilov.shikimori.domain.interactors.filteredanimes.FilteredAnimesInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface FilteredAnimesPageModule {
    @Binds
    @FilteredAnimesPageScope
    fun bindFilteredAnimesInteractor(filteredAnimesInteractorImpl: FilteredAnimesInteractorImpl): FilteredAnimesInteractor
}