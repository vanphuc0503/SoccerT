package com.example.soccert.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.*

open class RxViewModel : ViewModel() {
    protected val disposables: CompositeDisposable = CompositeDisposable()
    protected val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    protected val _errorApp = MutableLiveData<String>()
    val errorApp: LiveData<String>
        get() = _errorApp


    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            onLoadFail(throwable)
        }
    }
    protected val viewModelScopeExceptionHandler = viewModelScope + exceptionHandler

    private fun onLoadFail(throwable: Throwable) {
        throwable.printStackTrace()
        _errorApp.value = throwable.message.toString()
    }

    protected fun <T> executeAction(action: suspend () -> T, callback: (result: T) -> Unit = {}) {
        viewModelScopeExceptionHandler.launch {
            try {
                val t = action()
                callback(t)
            } catch (ex: Throwable) {
                throw UnsupportedOperationException("error internet")
            }
        }
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}
