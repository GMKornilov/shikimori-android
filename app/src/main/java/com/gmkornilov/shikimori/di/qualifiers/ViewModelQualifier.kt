package com.gmkornilov.shikimori.di.qualifiers

import androidx.lifecycle.ViewModel
import javax.inject.Qualifier
import kotlin.reflect.KClass

@Qualifier
annotation class ViewModelQualifier(val kClass: KClass<out ViewModel>)
