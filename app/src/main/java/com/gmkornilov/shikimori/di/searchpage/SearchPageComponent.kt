package com.gmkornilov.shikimori.di.searchpage

import com.gmkornilov.shikimori.di.app.AppComponent
import com.gmkornilov.shikimori.presentation.searchpage.SearchPageFragment
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [SearchPageModule::class])
@SearchPageScope
interface SearchPageComponent {
    fun inject(searchPageFragment: SearchPageFragment)
}