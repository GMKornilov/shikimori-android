package com.gmkornilov.shikimori.presentation.system.rx

import io.reactivex.rxjava3.core.Scheduler

interface SchedulersProvider {
    fun main(): Scheduler

    fun background(): Scheduler
}