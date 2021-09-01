package com.gmkornilov.shikimori.di.filteredanimespage

import com.gmkornilov.shikimori.di.app.AppComponent
import com.gmkornilov.shikimori.di.backstack.BackstackComponent
import com.gmkornilov.shikimori.presentation.filteredanimespage.FilteredAnimesFragment
import dagger.Component

@Component(
    modules = [FilteredAnimesPageModule::class],
    dependencies = [AppComponent::class, BackstackComponent::class]
)
@FilteredAnimesPageScope
interface FilteredAnimesPageComponent {
    fun inject(filteredAnimesFragment: FilteredAnimesFragment)
}
