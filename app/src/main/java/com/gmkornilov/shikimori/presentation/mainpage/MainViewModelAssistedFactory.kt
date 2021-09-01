package com.gmkornilov.shikimori.presentation.mainpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import dagger.assisted.AssistedFactory

class MainViewModelFactory (
    private val router: Router,
    private val mainViewModelAssistedFactory: MainViewModelAssistedFactory,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return mainViewModelAssistedFactory.create(router) as T
    }
}

@AssistedFactory
interface MainViewModelAssistedFactory {
    fun create(router: Router): MainViewModel
}