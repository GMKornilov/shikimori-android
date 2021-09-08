package com.gmkornilov.shikimori.presentation.searchpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.domain.interactors.searchpage.SearchPageInteractor
import com.gmkornilov.shikimori.presentation.items.animepreview.AnimePreview
import com.gmkornilov.shikimori.presentation.items.animepreview.AnimePreviewClicked
import com.gmkornilov.shikimori.presentation.items.animepreview.toPresentationAnimePreview
import com.gmkornilov.shikimori.presentation.navigation.Screens
import com.gmkornilov.shikimori.presentation.navigation.arguments.toDomainAnimeFilter
import com.gmkornilov.shikimori.presentation.system.rx.SchedulersProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

class SearchPageViewModel @AssistedInject constructor(
    private val searchPageInteractor: SearchPageInteractor,
    private val schedulersProvider: SchedulersProvider,
    @Assisted private val router: Router,
) : ViewModel(), AnimePreviewClicked {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _exception = MutableLiveData(false)
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

    private var disposable: Disposable? = null

    fun loadPreviews(query: String) {
        disposable?.dispose()
        disposable = searchPageInteractor.loadAnimesByQuery(query, SEARCH_LIMIT)
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
    }

    override fun onCleared() {
        super.onCleared()

        disposable?.dispose()
    }

    override fun onClicked(animePreview: AnimePreview) {
        router.navigateTo(Screens.AnimeScreen(animePreview))
    }

    companion object {
        private const val SEARCH_LIMIT = 16
    }
}