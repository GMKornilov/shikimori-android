package com.gmkornilov.shikimori.di.mainpage

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Router
import com.gmkornilov.shikimori.di.app.GlobalNavigation
import com.gmkornilov.shikimori.di.qualifiers.ViewModelQualifier
import com.gmkornilov.shikimori.domain.interactors.mainpage.MainPageInteractor
import com.gmkornilov.shikimori.presentation.mainpage.MainViewModel
import com.gmkornilov.shikimori.presentation.system.rx.SchedulersProvider
import dagger.Module
import dagger.Provides

@Module
object ViewModelModule {
    @Provides
    @MainPageScope
    @ViewModelQualifier(MainViewModel::class)
    fun provideViewModelFactory(
        mainPageInteractor: MainPageInteractor,
        schedulersProvider: SchedulersProvider,
        @GlobalNavigation router: Router
    ): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(
                    mainPageInteractor = mainPageInteractor,
                    schedulersProvider = schedulersProvider,
                    router = router,
                ) as T
            }
        }
    }
}