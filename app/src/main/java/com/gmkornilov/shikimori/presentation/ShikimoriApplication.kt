package com.gmkornilov.shikimori.presentation

import android.app.Application
import com.gmkornilov.shikimori.di.app.AppComponent
import com.gmkornilov.shikimori.di.app.DaggerAppComponent
import com.gmkornilov.shikimori.di.filteredanimespage.DaggerFilteredAnimesPageComponent
import com.gmkornilov.shikimori.di.filteredanimespage.FilteredAnimesPageComponent
import com.gmkornilov.shikimori.di.mainpage.DaggerMainPageComponent
import com.gmkornilov.shikimori.di.mainpage.MainPageComponent
import com.gmkornilov.shikimori.di.searchpage.DaggerSearchPageComponent
import com.gmkornilov.shikimori.di.searchpage.SearchPageComponent
import com.gmkornilov.shikimori.presentation.navigation.backstacks.BackstackNavigationManager

class ShikimoriApplication : Application() {
    private var _appComponent: AppComponent? = null
    val appComponent: AppComponent
        get() = _appComponent!!

    private var mainPageComponent: MainPageComponent? = null
    private var filteredAnimesPageComponent: FilteredAnimesPageComponent? = null
    private var searchPageComponent: SearchPageComponent? = null

    fun plusMainPageComponent(): MainPageComponent {
        if (mainPageComponent == null) {
            mainPageComponent = DaggerMainPageComponent.builder()
                .appComponent(appComponent)
                .build()
        }
        return mainPageComponent!!
    }

    fun clearMainPageComponent() {
        mainPageComponent = null
    }

    fun plusFilteredAnimesPageComponent(): FilteredAnimesPageComponent {
        if (filteredAnimesPageComponent == null) {
            filteredAnimesPageComponent = DaggerFilteredAnimesPageComponent.builder()
                .appComponent(appComponent)
                .build()
        }
        return filteredAnimesPageComponent!!
    }

    fun clearFilteredAnimesPageComponent() {
        filteredAnimesPageComponent = null
    }

    fun plusSearchPageComponent(): SearchPageComponent {
        if (searchPageComponent == null) {
            searchPageComponent = DaggerSearchPageComponent.builder()
                .appComponent(appComponent)
                .build()
        }
        return searchPageComponent!!
    }

    fun clearSearchPageComponent() {
        searchPageComponent = null
    }

    override fun onCreate() {
        super.onCreate()

        UNSAFE_INSTANCE = this

        _appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }

    companion object {
        private var UNSAFE_INSTANCE: ShikimoriApplication? = null
        val INSTANCE
            get() = UNSAFE_INSTANCE!!
    }
}