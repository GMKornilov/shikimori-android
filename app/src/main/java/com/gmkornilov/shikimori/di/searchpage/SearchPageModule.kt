package com.gmkornilov.shikimori.di.searchpage

import com.gmkornilov.shikimori.domain.interactors.searchpage.SearchPageInteractor
import com.gmkornilov.shikimori.domain.interactors.searchpage.SearchPageInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface SearchPageModule {
    @Binds
    @SearchPageScope
    fun bindSearchPageInteractor(searchPageInteractorImpl: SearchPageInteractorImpl): SearchPageInteractor
}