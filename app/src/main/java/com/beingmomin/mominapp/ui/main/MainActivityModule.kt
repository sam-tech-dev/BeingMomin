package com.beingmomin.mominapp.ui.main

import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun provideMainActivityModule(dataManager: DataManager,schedulerProvider: SchedulerProvider): MainViewModel {
        return MainViewModel(dataManager,schedulerProvider)
    }

}