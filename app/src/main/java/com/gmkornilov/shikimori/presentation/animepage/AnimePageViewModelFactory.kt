package com.gmkornilov.shikimori.presentation.animepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import dagger.assisted.AssistedFactory

class AnimePageViewModelFactory(
    private val id: Long,
    private val router: Router,
    private val assistedFactory: AnimePageViewModelAssistedFactory,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return assistedFactory.create(id, router) as T
    }
}

@AssistedFactory
interface AnimePageViewModelAssistedFactory {
    fun create(id: Long, router: Router): AnimePageViewModel
}