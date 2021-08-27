package com.gmkornilov.shikimori.di.mainpage

import com.gmkornilov.shikimori.di.app.AppScope
import com.gmkornilov.shikimori.domain.interactors.mainpage.MainPageInteractor
import com.gmkornilov.shikimori.domain.interactors.mainpage.MainPageInteractorImpl
import dagger.Binds
import dagger.Module

@Module
interface MainPageModule {
    @Binds
    @MainPageScope
    fun bindMainPageInteractor(mainPageInteractorImpl: MainPageInteractorImpl): MainPageInteractor
}