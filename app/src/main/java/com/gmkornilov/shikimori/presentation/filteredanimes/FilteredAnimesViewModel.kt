package com.gmkornilov.shikimori.presentation.filteredanimes

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilteredAnimesViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val filter: AnimeFilter = savedStateHandle.get<AnimeFilter>(FilteredAnimesFragment.filterKey)!!
}