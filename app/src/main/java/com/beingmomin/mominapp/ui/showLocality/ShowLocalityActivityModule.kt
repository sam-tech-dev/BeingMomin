package com.beingmomin.mominapp.ui.showLocality

import com.beingmomin.mominapp.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class ShowLocalityActivityModule {

    @Provides
    fun provideShowLocalityActivityModule(schedulerProvider: SchedulerProvider): ShowLocalityViewModel {
        return ShowLocalityViewModel(schedulerProvider)
    }

}