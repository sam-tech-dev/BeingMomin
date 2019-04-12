package com.beingmomin.mominapp.ui.signIn

import android.content.Context
import com.beingmomin.mominapp.utils.rx.SchedulerProvider
import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.di.annotations.ActivityContext
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference

@Module
class SignInActivityModule {

    @ActivityContext
    @Provides
    fun provideActivityContext(context: Context): Context {
        return  context
    }

    @Provides
    fun provideSignInViewModel( dataManager: DataManager, schedulerProvider: SchedulerProvider): SignInViewModel {
        return  SignInViewModel(dataManager,schedulerProvider)
    }

    @Provides
    fun provideNavigator(activity: SignInActivity): WeakReference<*> {
        return WeakReference(activity)
    }
}