package com.gmkornilov.shikimori.presentation.system.rx

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class AndroidSchedulersProvider @Inject constructor() : SchedulersProvider {
    override fun main(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun background(): Scheduler {
        return Schedulers.io()
    }
}