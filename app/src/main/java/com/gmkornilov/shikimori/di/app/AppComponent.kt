package com.gmkornilov.shikimori.di.app

import android.content.Context
import com.gmkornilov.shikimori.di.filteredanimespage.FilteredAnimesPageComponent
import com.gmkornilov.shikimori.di.mainpage.MainPageComponent
import com.gmkornilov.shikimori.di.mainpage.MainPageModule
import com.gmkornilov.shikimori.presentation.MainActivity
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
    fun inject(mainActivity: MainActivity)

    fun mainPageComponent(): MainPageComponent.Builder

    fun filteredAnimesPageComment(): FilteredAnimesPageComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(@ApplicationContext context: Context): Builder

        fun build(): AppComponent
    }
}