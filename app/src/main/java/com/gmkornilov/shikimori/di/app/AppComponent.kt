package com.gmkornilov.shikimori.di.app

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.domain.repositories.AnimeRepository
import com.gmkornilov.shikimori.presentation.mainactivity.MainActivity
import com.gmkornilov.shikimori.presentation.navigation.backstacks.BackstackFragment
import com.gmkornilov.shikimori.presentation.navigation.backstacks.BackstackNavigationManager
import com.gmkornilov.shikimori.presentation.system.rx.SchedulersProvider
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        DataSourceModule::class,
        NavigationModule::class,
        RxModule::class,
        NetworkModule::class
    ],
)
@AppScope
interface AppComponent {
    @get:GlobalNavigation val navigationHolder: NavigatorHolder
    @get:GlobalNavigation val router: Router

    val animeRepository: AnimeRepository

    val schedulersProvider: SchedulersProvider

    val backstackNavigationManager: BackstackNavigationManager

    @get:ApplicationContext val context: Context

    fun inject(mainActivity: MainActivity)

    fun inject(backstackFragment: BackstackFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(@ApplicationContext context: Context): Builder

        fun build(): AppComponent
    }
}