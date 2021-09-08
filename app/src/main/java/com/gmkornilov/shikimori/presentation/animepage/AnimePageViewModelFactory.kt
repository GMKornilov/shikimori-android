package com.gmkornilov.shikimori.presentation.animepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory

class AnimePageViewModelFactory(
    private val id: Long,
    private val assistedFactory: AnimePageViewModelAssistedFactory,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return assistedFactory.create(id) as T
    }
}

@AssistedFactory
interface AnimePageViewModelAssistedFactory {
    fun create(id: Long): AnimePageViewModel
}