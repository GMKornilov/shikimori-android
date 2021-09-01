package com.gmkornilov.shikimori.di.backstack

import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.di.app.AppComponent
import com.gmkornilov.shikimori.presentation.navigation.backstacks.BackstackFragment
import dagger.Component

@Component(modules = [BackstackNavigationModule::class], dependencies = [AppComponent::class])
@BackstackScope
interface BackstackComponent {
    @get:BackstackNavigation val navigationHolder: NavigatorHolder
    @get:BackstackNavigation val router: Router

    fun inject(backstackFragment: BackstackFragment)
}