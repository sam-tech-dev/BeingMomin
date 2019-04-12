package com.beingmomin.mominapp.ui.ambassadorDescription

import com.beingmomin.mominapp.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class AmbassadorDescriptionActivityModule {

    @Provides
    fun provideAmbassadorDescriptionActivityModule(schedulerProvider: SchedulerProvider): AmbassadorDescriptionViewModel {
        return AmbassadorDescriptionViewModel(schedulerProvider)
    }

}