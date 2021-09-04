package com.gmkornilov.shikimori.di.animepage

import com.gmkornilov.shikimori.di.app.AppComponent
import com.gmkornilov.shikimori.presentation.animepage.AnimePageFragment
import dagger.Component

@Component(
    dependencies = [AppComponent::class],
    modules = [AnimePageModule::class]
)
@AnimePageScope
interface AnimePageComponent {
    fun inject(animePageFragment: AnimePageFragment)
}