package com.gmkornilov.shikimori.di.mainpage

import com.gmkornilov.shikimori.presentation.mainpage.MainFragment
import dagger.Subcomponent

@Subcomponent(modules = [MainPageModule::class, ViewModelModule::class])
@MainPageScope
interface MainPageComponent {
    fun inject(mainFragment: MainFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build(): MainPageComponent
    }
}