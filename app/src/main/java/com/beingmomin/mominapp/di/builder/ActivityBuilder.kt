package com.beingmomin.mominapp.di.builder

import com.beingmomin.mominapp.ui.ambassadorDescription.AmbassadorDescriptionActivity
import com.beingmomin.mominapp.ui.applyForAmbassador.ApplyForAmbassadorActivity
import com.beingmomin.mominapp.ui.localitySearch.LocalitySearchActivity
import com.beingmomin.mominapp.ui.localitySearch.LocalitySearchActivityModule
import com.beingmomin.mominapp.ui.main.MainActivity
import com.beingmomin.mominapp.ui.main.MainActivityModule
import com.beingmomin.mominapp.ui.showLocality.ShowLocalityActivity
import com.beingmomin.mominapp.ui.signIn.SignInActivity
import com.beingmomin.mominapp.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun bindSignInActivity(): SignInActivity

    @ContributesAndroidInjector
    abstract fun bindAmbassadorDescriptionActivity(): AmbassadorDescriptionActivity

    @ContributesAndroidInjector(modules = arrayOf(LocalitySearchActivityModule::class))
    abstract fun bindLocalitySearchActivity(): LocalitySearchActivity

    @ContributesAndroidInjector
    abstract fun bindShowLocalityActivity(): ShowLocalityActivity

    @ContributesAndroidInjector
    abstract fun bindApplyForAmbassadorActivity(): ApplyForAmbassadorActivity

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuilder::class, MainActivityModule::class))
    abstract fun bindMainActivity(): MainActivity

}