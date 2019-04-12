package com.beingmomin.mominapp.ui.splash

import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

    @Provides
    fun provideSplashViewModel(dataManager: DataManager,schedulerProvider: SchedulerProvider): SplashViewModel {
        return SplashViewModel(dataManager,schedulerProvider)
    }
}