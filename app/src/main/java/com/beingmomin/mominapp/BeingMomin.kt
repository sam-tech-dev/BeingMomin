package com.beingmomin.mominapp

import android.app.Activity
import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by amitshekhar on 07/07/17.
 */

class BeingMomin : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit  var dataManager:DataManager

    companion object {

        lateinit var app :BeingMomin

        fun getInstance(): BeingMomin{
            return  app
        }
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        app=this

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)

        AndroidNetworking.initialize(applicationContext);

    }


}
