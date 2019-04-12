package com.beingmomin.mominapp.di.builder

import com.beingmomin.mominapp.ui.localitySearch.LocalitySearchActivity
import com.beingmomin.mominapp.ui.localitySearch.LocalitySearchActivityModule
import com.beingmomin.mominapp.ui.showLocality.ShowLocalityActivity
import com.beingmomin.mominapp.ui.showLocality.ShowLocalityActivityModule
import com.beingmomin.mominapp.ui.ambassadorDescription.AmbassadorDescriptionActivity
import com.beingmomin.mominapp.ui.ambassadorDescription.AmbassadorDescriptionActivityModule
import com.beingmomin.mominapp.ui.applyForAmbassador.ApplyForAmbassadorActivity
import com.beingmomin.mominapp.ui.applyForAmbassador.ApplyForAmbassadorActivityModule
import com.beingmomin.mominapp.ui.main.MainActivity
import com.beingmomin.mominapp.ui.main.MainActivityModule
import com.beingmomin.mominapp.ui.signIn.SignInActivity
import com.beingmomin.mominapp.ui.signIn.SignInActivityModule
import com.beingmomin.mominapp.ui.splash.SplashActivity
import com.beingmomin.mominapp.ui.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(SplashActivityModule::class))
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = arrayOf(SignInActivityModule::class))
    abstract fun bindSignInActivity(): SignInActivity

    @ContributesAndroidInjector(modules = arrayOf(AmbassadorDescriptionActivityModule::class))
    abstract fun bindAmbassadorDescriptionActivity(): AmbassadorDescriptionActivity

    @ContributesAndroidInjector(modules = arrayOf(LocalitySearchActivityModule::class))
    abstract fun bindLocalitySearchActivity(): LocalitySearchActivity

    @ContributesAndroidInjector(modules = arrayOf(ShowLocalityActivityModule::class))
    abstract fun bindShowLocalityActivity(): ShowLocalityActivity

    @ContributesAndroidInjector(modules = arrayOf(ApplyForAmbassadorActivityModule::class))
    abstract fun bindApplyForAmbassadorActivity(): ApplyForAmbassadorActivity

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun bindMainActivity(): MainActivity

}