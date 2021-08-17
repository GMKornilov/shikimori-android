package com.gmkornilov.shikimori.di.modules

import com.gmkornilov.shikimori.presentation.system.rx.AndroidSchedulersProvider
import com.gmkornilov.shikimori.presentation.system.rx.SchedulersProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RxModule {
    @Binds
    fun bindAndroidSchedulersProvider(androidSchedulersProvider: AndroidSchedulersProvider): SchedulersProvider
}