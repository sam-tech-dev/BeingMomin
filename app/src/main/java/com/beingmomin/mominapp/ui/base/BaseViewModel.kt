package com.beingmomin.mominapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableBoolean
import android.util.Log
import com.beingmomin.mominapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference


abstract class BaseViewModel<N> constructor(var mSchedulerProvider:SchedulerProvider) : ViewModel() {


    lateinit var mNavigator: WeakReference<N>

    private val mIsLoading = ObservableBoolean(false)

    private val mCompositeDisposable: CompositeDisposable

    init {
        mCompositeDisposable = CompositeDisposable()
    }

    fun getIsLoading(): ObservableBoolean {
        return mIsLoading
    }

    fun setIsLoading(isLoading: Boolean) {
        mIsLoading.set(isLoading)
    }


    fun getNavigator(): N? {
        return mNavigator.get()
    }

    fun setNavigator(navigator: N) {
        this.mNavigator = WeakReference(navigator)
    }

    fun log(tag: String, msg: String) {
        Log.d(tag, msg)
    }

    fun log(msg: String) {
        Log.d("azz", msg)
    }

    fun getCompositeDisposable(): CompositeDisposable {
        return mCompositeDisposable
    }

    fun getSchedulerProvider(): SchedulerProvider {
        return mSchedulerProvider
    }

    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }


}