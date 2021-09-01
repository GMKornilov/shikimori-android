package com.gmkornilov.shikimori.presentation

import android.app.Application
import com.gmkornilov.shikimori.di.app.AppComponent
import com.gmkornilov.shikimori.di.app.DaggerAppComponent
import com.gmkornilov.shikimori.di.backstack.DaggerBackstackComponent
import com.gmkornilov.shikimori.di.filteredanimespage.DaggerFilteredAnimesPageComponent
import com.gmkornilov.shikimori.di.filteredanimespage.FilteredAnimesPageComponent
import com.gmkornilov.shikimori.di.mainpage.DaggerMainPageComponent
import com.gmkornilov.shikimori.di.mainpage.MainPageComponent
import com.gmkornilov.shikimori.presentation.navigation.backstacks.BackstackComponentManager

class ShikimoriApplication : Application() {
    private var _appComponent: AppComponent? = null
    val appComponent: AppComponent
        get() = _appComponent!!
    private var _backstackComponentManager: BackstackComponentManager? = null
    val backstackComponentManager: BackstackComponentManager
        get() = _backstackComponentManager!!

    private var mainPageComponent: MainPageComponent? = null
    private var filteredAnimesPageComponent: FilteredAnimesPageComponent? = null

    fun plusMainPageComponent(): MainPageComponent {
        if (mainPageComponent == null) {
            mainPageComponent = DaggerMainPageComponent.builder()
                .appComponent(appComponent)
                .backstackComponent(backstackComponentManager.getCurrentBackstackComponent())
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
                .backstackComponent(backstackComponentManager.getCurrentBackstackComponent())
                .build()
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

        _backstackComponentManager = BackstackComponentManager(appComponent)
    }

    companion object {
        private var _instance: ShikimoriApplication? = null
        val instance
            get() = _instance!!
    }
}