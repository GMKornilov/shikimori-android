package com.gmkornilov.shikimori.presentation.navigation.backstacks

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router

class BackstackNavigationManager {
    private val backstacks = mutableMapOf<String, Cicerone<Router>>()

    private var _currentBackstack: String? = null
    private val currentBackstack: String
        get() = _currentBackstack!!

    fun getCurrentBackstackRouter(backstackName: String): Router {
        _currentBackstack = backstackName
        return getCurrentBackstackRouter()
    }

    fun getCurrentBackstackNavigatorHolder(backstackName: String): NavigatorHolder {
        _currentBackstack = backstackName
        return getCurrentBackstackNavigatorHolder()
    }

    fun getCurrentBackstackRouter(): Router {
        return getRouter(backstacks.getOrPut(currentBackstack) {
            Cicerone.create()
        })
    }

    fun getCurrentBackstackNavigatorHolder(): NavigatorHolder {
        return getNavigatorHolder(backstacks.getOrPut(currentBackstack) {
            Cicerone.create()
        })
    }

    private fun getRouter(cicerone: Cicerone<Router>): Router {
        return cicerone.router
    }

    private fun getNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder {
        return cicerone.getNavigatorHolder()
    }

    fun changeBackstack(backstackName: String) {
        _currentBackstack = backstackName
    }

    fun clearCurrentBackstackComponent() {
        backstacks.remove(currentBackstack)
        _currentBackstack = null
    }
}