package com.beingmomin.mominapp.ui.applyForAmbassador

import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class ApplyForAmbassadorActivityModule {

    @Provides
    fun provideShowLocalityActivityModule(dataManager: DataManager, schedulerProvider: SchedulerProvider): ApplyForAmbassadorViewModel {
        return ApplyForAmbassadorViewModel(dataManager,schedulerProvider)
    }

}