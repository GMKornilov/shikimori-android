package com.gmkornilov.shikimori.presentation

import android.app.Application
import com.gmkornilov.shikimori.di.app.AppComponent
import com.gmkornilov.shikimori.di.app.DaggerAppComponent
import com.gmkornilov.shikimori.di.filteredanimespage.FilteredAnimesPageComponent
import com.gmkornilov.shikimori.di.mainpage.MainPageComponent

class ShikimoriApplication : Application() {
    private var _appComponent: AppComponent? = null
    val appComponent: AppComponent
        get() = _appComponent!!

    private var mainPageComponent: MainPageComponent? = null
    private var filteredAnimesPageComponent: FilteredAnimesPageComponent? = null

    fun plusMainPageComponent(): MainPageComponent {
        if (mainPageComponent == null) {
            mainPageComponent = appComponent.mainPageComponent().build()
        }
        return mainPageComponent!!
    }

    fun clearMainPageComponent() {
        mainPageComponent = null
    }

    fun plusFilteredAnimesPageComponent(): FilteredAnimesPageComponent {
        if (filteredAnimesPageComponent == null) {
            filteredAnimesPageComponent = appComponent.filteredAnimesPageComment().build()
        }
        return filteredAnimesPageComponent!!
    }

    fun clearFilteredAnimesPageComponent() {
        filteredAnimesPageComponent = null
    }

    override fun onCreate() {
        super.onCreate()

        _instance = this

        _appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }

    companion object {
        private var _instance: ShikimoriApplication? = null
        val instance
            get() = _instance!!
    }
}