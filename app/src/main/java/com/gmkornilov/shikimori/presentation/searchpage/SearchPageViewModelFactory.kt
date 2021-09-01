package com.gmkornilov.shikimori.presentation.searchpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import dagger.assisted.AssistedFactory

class SearchPageViewModelFactory(
    private val router: Router,
    private val assistedFactory: SearchPageViewModelAssistedFactory,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return assistedFactory.create(router) as T
    }
}

@AssistedFactory
interface SearchPageViewModelAssistedFactory {
    fun create(router: Router): SearchPageViewModel
}