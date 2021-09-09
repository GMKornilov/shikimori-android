package com.gmkornilov.shikimori.presentation.animepage

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.di.app.ApplicationContext
import com.gmkornilov.shikimori.domain.interactors.animepage.AnimePageInteractor
import com.gmkornilov.shikimori.domain.models.common.AnimeInfo
import com.gmkornilov.shikimori.presentation.components.BaseComponent
import com.gmkornilov.shikimori.presentation.extensions.toAnimePageItems
import com.gmkornilov.shikimori.presentation.lazyloaders.SingleLazyLoader
import com.gmkornilov.shikimori.presentation.navigation.Screens
import com.gmkornilov.shikimori.presentation.system.rx.SchedulersProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.serialization.ExperimentalSerializationApi

@SuppressLint("StaticFieldLeak")
class AnimePageViewModel @AssistedInject constructor(
    private val schedulersProvider: SchedulersProvider,
    private val animePageInteractor: AnimePageInteractor,
    // this field is not leaked because we inject application context,
    // which "lives" longer than view model
    @ApplicationContext private val context: Context,
    @Assisted private val animeId: Long,
    @Assisted private val router: Router,
) : ViewModel() {
    @ExperimentalSerializationApi
    val loadingData: SingleLazyLoader<AnimeInfo, List<BaseComponent>> by lazy {
        SingleLazyLoader(
            schedulersProvider,
            { animePageInteractor.loadAnime(animeId) }
        ) {
            it.toAnimePageItems(context)
        }
    }

    fun showVideo(url:String) {
        router.navigateTo(Screens.Video(url))
    }

    override fun onCleared() {
        super.onCleared()

        loadingData.destroy()
    }
}