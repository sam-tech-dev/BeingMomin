package com.beingmomin.mominapp.di.component

import android.app.Application
import com.beingmomin.mominapp.BeingMomin
import com.beingmomin.mominapp.di.builder.ActivityBuilder
import com.beingmomin.mominapp.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(app: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: BeingMomin)
}