package com.gmkornilov.shikimori.di.filteredanimespage

import com.gmkornilov.shikimori.presentation.filteredanimespage.FilteredAnimesFragment
import dagger.Subcomponent

@Subcomponent(modules = [FilteredAnimesPageModule::class])
@FilteredAnimesPageScope
interface FilteredAnimesPageComponent {
    fun inject(filteredAnimesFragment: FilteredAnimesFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): FilteredAnimesPageComponent
    }
}
