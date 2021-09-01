package com.gmkornilov.shikimori.di.app

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.di.backstack.BackstackComponent
import com.gmkornilov.shikimori.di.backstack.BackstackNavigationModule
import com.gmkornilov.shikimori.di.filteredanimespage.FilteredAnimesPageComponent
import com.gmkornilov.shikimori.di.mainpage.MainPageComponent
import com.gmkornilov.shikimori.di.mainpage.MainPageModule
import com.gmkornilov.shikimori.domain.repositories.AnimeRepository
import com.gmkornilov.shikimori.presentation.MainActivity
import com.gmkornilov.shikimori.presentation.system.rx.SchedulersProvider
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        DataSourceModule::class,
        NavigationModule::class,
        RxModule::class
    ],
)
@AppScope
interface AppComponent {
    @get:GlobalNavigation val navigationHolder: NavigatorHolder
    @get:GlobalNavigation val router: Router

    val animeRepository: AnimeRepository

    val schedulersProvider: SchedulersProvider

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(@ApplicationContext context: Context): Builder

        fun build(): AppComponent
    }
}