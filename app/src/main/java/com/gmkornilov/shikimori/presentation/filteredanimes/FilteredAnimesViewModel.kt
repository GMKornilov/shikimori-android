package com.gmkornilov.shikimori.presentation.filteredanimes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.domain.interactors.filteredanimes.FilteredAnimesInteractor
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimePreview
import com.gmkornilov.shikimori.presentation.filteredanimes.adapter.AnimePreviewClicked
import com.gmkornilov.shikimori.presentation.navigation.Screens
import com.gmkornilov.shikimori.presentation.system.rx.SchedulersProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class FilteredAnimesViewModel @Inject constructor(
    private val filteredAnimesInteractor: FilteredAnimesInteractor,
    private val schedulersProvider: SchedulersProvider,
    private val router: Router,
    savedStateHandle: SavedStateHandle,
) : ViewModel(), AnimePreviewClicked {
    private var filter: AnimeFilter = savedStateHandle.get<AnimeFilter>(FilteredAnimesFragment.filterKey)!!

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _exception = MutableLiveData<Boolean>()
    val exception: LiveData<Boolean> = _exception

    private val _previews = MutableLiveData<List<AnimePreview>>()
    val previews: LiveData<List<AnimePreview>> = _previews

    private val compositeDisposable = CompositeDisposable()

    fun loadPreviews() {
        val disposable = filteredAnimesInteractor.loadAnimesByFilter(filter)
            .subscribeOn(schedulersProvider.background())
            .observeOn(schedulersProvider.main())
            .doOnSubscribe {
                _loading.value = true
            }
            .doAfterTerminate {
                _loading.value = false
            }
            .subscribe { previewsList, throwable ->
                if (throwable != null) {
                    _exception.value = true
                } else {
                    _previews.value = previewsList
                }
            }
        compositeDisposable.add(disposable)
    }


    init {
        loadPreviews()
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }

    override fun animePreviewClicked(animePreview: AnimePreview) {
        router.navigateTo(Screens.AnimeScreen(animePreview.id))
    }
}