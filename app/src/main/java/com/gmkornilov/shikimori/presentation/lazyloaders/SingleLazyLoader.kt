package com.gmkornilov.shikimori.presentation.lazyloaders

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.gmkornilov.shikimori.data.http.RequestResult
import com.gmkornilov.shikimori.data.http.mapSuccessful
import com.gmkornilov.shikimori.presentation.system.rx.SchedulersProvider
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable

class SingleLazyLoader<T : Any, R : Any>(
    private val schedulersProvider: SchedulersProvider,
    private val loader: () -> Single<RequestResult<T>>,
    private val mapper: (T) -> R,
) {
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _exception = MutableLiveData(false)
    val exception: LiveData<Boolean> = _exception

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

    private val _values: MutableLiveData<R> = MutableLiveData()
    val values: LiveData<R> = _values

    private var compositeDisposable = CompositeDisposable()

    init {
        load()
    }

    fun load() {
        val disposable = loader()
            .mapSuccessful {
                mapper(it)
            }
            .subscribeOn(schedulersProvider.background())
            .observeOn(schedulersProvider.main())
            .doOnSubscribe {
                _exception.value = false
                _loading.value = true
            }
            .doAfterTerminate {
                _loading.value = false
            }
            .subscribe { result ->
                when (result) {
                    is RequestResult.Success -> _values.value = result.data
                    else -> _exception.value = true
                }
            }
        compositeDisposable.add(disposable)
    }

    fun destroy() {
        compositeDisposable.dispose()
    }

    companion object {
        private const val TAG = "SingleLazyLoader"
    }
}