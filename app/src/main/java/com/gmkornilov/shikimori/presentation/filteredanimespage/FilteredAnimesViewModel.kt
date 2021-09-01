package com.gmkornilov.shikimori.presentation.filteredanimespage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.domain.interactors.filteredanimes.FilteredAnimesInteractor
import com.gmkornilov.shikimori.presentation.items.animepreview.AnimePreview
import com.gmkornilov.shikimori.presentation.items.animepreview.AnimePreviewClicked
import com.gmkornilov.shikimori.presentation.items.animepreview.toPresentationAnimePreview
import com.gmkornilov.shikimori.presentation.navigation.Screens
import com.gmkornilov.shikimori.presentation.navigation.arguments.AnimeFilter
import com.gmkornilov.shikimori.presentation.navigation.arguments.toDomainAnimeFilter
import com.gmkornilov.shikimori.presentation.system.rx.SchedulersProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.CompositeDisposable

class FilteredAnimesViewModel @AssistedInject constructor(
    private val filteredAnimesInteractor: FilteredAnimesInteractor,
    private val schedulersProvider: SchedulersProvider,
    @Assisted private val router: Router,
    @Assisted private val filter: AnimeFilter,
) : ViewModel(), AnimePreviewClicked {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _exception = MutableLiveData<Boolean>()
    val exception: LiveData<Boolean> = _exception

    private val _previews = MutableLiveData<List<AnimePreview>>()
    val previews: LiveData<List<AnimePreview>> = _previews

    private val _loadedWithoutErrors = MediatorLiveData<Boolean>().apply {
        fun update() {
            val loadingVal = _loading.value ?: return
            val errorVal = _exception.value ?: return

            value = !(loadingVal || errorVal)
        }
        addSource(_loading) { update() }
        addSource(_exception) { update() }
    }
    val loadedWithoutErrors: LiveData<Boolean> = _loadedWithoutErrors

    private val compositeDisposable = CompositeDisposable()

    fun loadPreviews() {
        val disposable = filteredAnimesInteractor.loadAnimesByFilter(filter.toDomainAnimeFilter())
            .subscribeOn(schedulersProvider.background())
            .map { list ->
                list.map {
                    it.toPresentationAnimePreview()
                }
            }
            .observeOn(schedulersProvider.main())
            .doOnSubscribe {
                _exception.value = false
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

    override fun onClicked(animePreview: AnimePreview) {
        router.navigateTo(Screens.AnimeScreen(animePreview.id))
    }
}