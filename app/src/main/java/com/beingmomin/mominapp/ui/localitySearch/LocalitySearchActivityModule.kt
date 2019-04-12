package com.beingmomin.mominapp.ui.localitySearch

import android.content.Context
import com.beingmomin.mominapp.utils.rx.SchedulerProvider
import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.di.annotations.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class LocalitySearchActivityModule {


    @ActivityContext
    @Provides
    fun provideActivityContext(context: Context): Context {
        return  context
    }


    @Provides
    fun provideLocalitySearchViewModel(dataManager: DataManager,schedulerProvider: SchedulerProvider): LocalitySearchViewModel {
        return LocalitySearchViewModel(dataManager,schedulerProvider)
    }

}