package com.gmkornilov.shikimori.di.mainpage

import com.gmkornilov.shikimori.di.app.AppComponent
import com.gmkornilov.shikimori.presentation.mainpage.MainFragment
import dagger.Component

@Component(
    modules = [MainPageModule::class],
    dependencies = [AppComponent::class]
)
@MainPageScope
interface MainPageComponent {
    fun inject(mainFragment: MainFragment)
}