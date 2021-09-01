package com.gmkornilov.shikimori.di.app

import com.gmkornilov.shikimori.presentation.system.rx.AndroidSchedulersProvider
import com.gmkornilov.shikimori.presentation.system.rx.SchedulersProvider
import dagger.Binds
import dagger.Module

@Module
interface RxModule {
    @Binds
    @AppScope
    fun bindAndroidSchedulersProvider(androidSchedulersProvider: AndroidSchedulersProvider): SchedulersProvider
}