package com.beingmomin.mominapp.di.module


import android.content.Context
import android.content.SharedPreferences
import com.beingmomin.mominapp.BeingMomin
import com.beingmomin.mominapp.utils.rx.AppSchedulerProvider
import com.beingmomin.mominapp.utils.rx.SchedulerProvider
import com.beingmomin.mominapp.data.preferences.AppPreferencesHelper
import com.beingmomin.mominapp.di.annotations.ApplicationContext
import com.beingmomin.mominapp.di.annotations.Token
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
class AppModule {

    @ApplicationContext
    @Provides
    internal  fun getApplicationContext(application: BeingMomin) : Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    internal  fun getApplication() : BeingMomin {
        return BeingMomin.getInstance()
    }


    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }


    @Singleton
    @Provides
    internal fun provideSharedPrefs(application: BeingMomin): SharedPreferences {
        return application.getSharedPreferences("app-prefs", Context.MODE_PRIVATE)
    }

    @Token
    @Singleton
    @Provides
    internal fun provideToken(appPreferencesHelper: AppPreferencesHelper): String{
        return   "GP "+appPreferencesHelper.getAccessToken()
    }


    @Provides
    internal fun provideScheduler(): SchedulerProvider{
        return AppSchedulerProvider()
    }

}
