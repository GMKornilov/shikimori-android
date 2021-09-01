package com.gmkornilov.shikimori.presentation.navigation.backstacks

import com.gmkornilov.shikimori.di.app.AppComponent
import com.gmkornilov.shikimori.di.backstack.BackstackComponent
import com.gmkornilov.shikimori.di.backstack.DaggerBackstackComponent
import javax.inject.Inject

class BackstackComponentManager (
    private val appComponent: AppComponent,
) {
    private val backstacks = mutableMapOf<String, BackstackComponent>()

    private var _currentBackstack: String? = null
    private val currentBackstack: String
        get() = _currentBackstack!!

    fun getCurrentBackstackComponent(backstackName: String): BackstackComponent {
        _currentBackstack = backstackName
        return getCurrentBackstackComponent()
    }

    fun getCurrentBackstackComponent(): BackstackComponent {
        return backstacks.getOrPut(currentBackstack) {
            DaggerBackstackComponent.builder().appComponent(appComponent).build()
        }
    }

    fun clearCurrentBackstackComponent() {
        _currentBackstack = null
    }
}