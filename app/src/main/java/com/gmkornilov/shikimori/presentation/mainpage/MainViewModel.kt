package com.gmkornilov.shikimori.presentation.mainpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.domain.interactors.mainpage.MainPageInteractor
import com.gmkornilov.shikimori.domain.models.common.AnimeFilter
import com.gmkornilov.shikimori.domain.models.common.AnimeOrder
import com.gmkornilov.shikimori.domain.models.common.AnimeStatus
import com.gmkornilov.shikimori.presentation.items.animepreview.AnimePreview
import com.gmkornilov.shikimori.presentation.items.animepreview.AnimePreviewClicked
import com.gmkornilov.shikimori.presentation.items.animepreview.toPresentationAnimePreview
import com.gmkornilov.shikimori.presentation.navigation.Screens
import com.gmkornilov.shikimori.presentation.navigation.arguments.toPresentationAnimeFilter
import com.gmkornilov.shikimori.presentation.system.rx.SchedulersProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject

class MainViewModel @AssistedInject constructor(
    private val mainPageInteractor: MainPageInteractor,
    private val schedulersProvider: SchedulersProvider,
    @Assisted private val router: Router,
) : ViewModel(), AnimePreviewClicked {
    private val _nowOnScreensLoading = MutableLiveData<Boolean>()
    val nowOnScreensLoading: LiveData<Boolean> = _nowOnScreensLoading

    private val _announcementsLoading = MutableLiveData<Boolean>()
    val announcementsLoading: LiveData<Boolean> = _announcementsLoading

    private val _mostPopularLoading = MutableLiveData<Boolean>()
    val mostPopularLoading: LiveData<Boolean> = _mostPopularLoading

    private val _mostRatedLoading = MutableLiveData<Boolean>()
    val mostRatedLoading: LiveData<Boolean> = _mostRatedLoading


    private val _nowOnScreensError = MutableLiveData<Boolean>()
    val nowOnScreensError: LiveData<Boolean> = _nowOnScreensError

    private val _announcementsError = MutableLiveData<Boolean>()
    val announcementsError: LiveData<Boolean> = _announcementsError

    private val _mostPopularError = MutableLiveData<Boolean>()
    val mostPopularError: LiveData<Boolean> = _mostPopularError

    private val _mostRatedError = MutableLiveData<Boolean>()
    val mostRatedError: LiveData<Boolean> = _mostRatedError


    private val _nowOnScreensLoadedWithoutErrors = MediatorLiveData<Boolean>().apply {
        fun update() {
            val loadingVal = _nowOnScreensLoading.value ?: return
            val errorVal = _nowOnScreensError.value ?: return

            value = !(loadingVal || errorVal)
        }
        addSource(_nowOnScreensLoading) { update() }
        addSource(_nowOnScreensError) { update() }
    }
    val nowOnScreensLoadedWithoutErrors: LiveData<Boolean> = _nowOnScreensLoadedWithoutErrors

    private val _announcementsLoadedWithoutErrors = MediatorLiveData<Boolean>().apply {
        fun update() {
            val loadingVal = _announcementsLoading.value ?: return
            val errorVal = _announcementsError.value ?: return

            value = !(loadingVal || errorVal)
        }
        addSource(_announcementsLoading) { update() }
        addSource(_announcementsError) { update() }
    }
    val announcementsLoadedWithoutErrors: LiveData<Boolean> = _announcementsLoadedWithoutErrors

    private val _mostPopularLoadedWithoutErrors = MediatorLiveData<Boolean>().apply {
        fun update() {
            val loadingVal = _mostPopularLoading.value ?: return
            val errorVal = _mostPopularError.value ?: return

            value = !(loadingVal || errorVal)
        }
        addSource(_mostPopularLoading) { update() }
        addSource(_mostPopularError) { update() }
    }
    val mostPopularLoadedWithoutErrors: LiveData<Boolean> = _mostPopularLoadedWithoutErrors

    private val _mostRatedLoadedWithoutErrors = MediatorLiveData<Boolean>().apply {
        fun update() {
            val loadingVal = _mostRatedLoading.value ?: return
            val errorVal = _mostRatedError.value ?: return

            value = !(loadingVal || errorVal)
        }
        addSource(_mostRatedLoading) { update() }
        addSource(_mostRatedError) { update() }
    }
    val mostRatedLoadedWithoutErrors: LiveData<Boolean> = _mostRatedLoadedWithoutErrors


    private val _nowOnScreens = MutableLiveData<List<AnimePreview>>()
    val nowOnScreens: LiveData<List<AnimePreview>> = _nowOnScreens

    private val _announcements = MutableLiveData<List<AnimePreview>>()
    val announcements: LiveData<List<AnimePreview>> = _announcements

    private val _mostPopular = MutableLiveData<List<AnimePreview>>()
    val mostPopular: LiveData<List<AnimePreview>> = _mostPopular

    private val _mostRated = MutableLiveData<List<AnimePreview>>()
    val mostRated: LiveData<List<AnimePreview>> = _mostRated

    private val compositeDisposable = CompositeDisposable()

    fun loadAll() {
        loadNowOnScreens()
        loadAnnouncements()
        loadMostPopular()
        loadMostRated()
    }

    fun loadNowOnScreens() {
        val disposable = mainPageInteractor.loadNowOnScreens()
            .subscribeOn(schedulersProvider.background())
            .map { list ->
                list.map {
                    it.toPresentationAnimePreview()
                }
            }
            .observeOn(schedulersProvider.main())
            .doOnSubscribe {
                _nowOnScreensLoading.value = true
                _nowOnScreensError.value = false
            }
            .doAfterTerminate {
                _nowOnScreensLoading.value = false
            }
            .subscribe { result, throwable ->
                if (throwable != null) {
                    _nowOnScreensError.value = true
                } else {
                    _nowOnScreens.value = result
                }
            }
        compositeDisposable.add(disposable)
    }

    fun loadAnnouncements() {
        val disposable = mainPageInteractor.loadAnnouncements()
            .subscribeOn(schedulersProvider.background())
            .map { list ->
                list.map {
                    it.toPresentationAnimePreview()
                }
            }
            .observeOn(schedulersProvider.main())
            .doOnSubscribe {
                _announcementsLoading.value = true
                _announcementsError.value = false
            }
            .doAfterTerminate {
                _announcementsLoading.value = false
            }
            .subscribe { result, throwable ->
                if (throwable != null) {
                    _announcementsError.value = true
                } else {
                    _announcements.value = result
                }
            }
        compositeDisposable.add(disposable)
    }

    fun loadMostPopular() {
        val disposable = mainPageInteractor.loadMostPopular()
            .subscribeOn(schedulersProvider.background())
            .map { list ->
                list.map {
                    it.toPresentationAnimePreview()
                }
            }
            .observeOn(schedulersProvider.main())
            .doOnSubscribe {
                _mostPopularLoading.value = true
                _mostPopularError.value = false
            }
            .doAfterTerminate {
                _mostPopularLoading.value = false
            }
            .subscribe { result, throwable ->
                if (throwable != null) {
                    _mostPopularError.value = true
                } else {
                    _mostPopular.value = result
                }
            }
        compositeDisposable.add(disposable)
    }

    fun loadMostRated() {
        val disposable = mainPageInteractor.loadMostRated()
            .subscribeOn(schedulersProvider.background())
            .map { list ->
                list.map {
                    it.toPresentationAnimePreview()
                }
            }
            .observeOn(schedulersProvider.main())
            .doOnSubscribe {
                _mostRatedLoading.value = true
                _mostRatedError.value = false
            }
            .doAfterTerminate {
                _mostRatedLoading.value = false
            }
            .subscribe { result, throwable ->
                if (throwable != null) {
                    _mostRatedError.value = true
                } else {
                    _mostRated.value = result
                }
            }
        compositeDisposable.add(disposable)
    }

    fun nowOnScreensClicked() {
        val filter = AnimeFilter.Builder()
            .status(AnimeStatus.ONGOING)
            .order(AnimeOrder.POPULARITY)
            .season(Calendar.getInstance().get(Calendar.YEAR).toString())
            .limit(20)
            .build()

        router.navigateTo(Screens.FilteredAnimesScreen(filter.toPresentationAnimeFilter()))
    }

    fun announcementsClicked() {
        val filter = AnimeFilter.Builder()
            .status(AnimeStatus.ANONS)
            .order(AnimeOrder.POPULARITY)
            .limit(20)
            .build()

        router.navigateTo(Screens.FilteredAnimesScreen(filter.toPresentationAnimeFilter()))
    }

    fun mostPopularClicked() {
        val filter = AnimeFilter.Builder()
            .order(AnimeOrder.POPULARITY)
            .limit(20)
            .build()

        router.navigateTo(Screens.FilteredAnimesScreen(filter.toPresentationAnimeFilter()))
    }

    fun mostRatedClicked() {
        val filter = AnimeFilter.Builder()
            .order(AnimeOrder.RANKED)
            .limit(20)
            .build()

        router.navigateTo(Screens.FilteredAnimesScreen(filter.toPresentationAnimeFilter()))
    }

    override fun onClicked(animePreview: AnimePreview) {
        router.navigateTo(Screens.AnimeScreen(animePreview.id))
    }

    init {
        loadAll()
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }
}