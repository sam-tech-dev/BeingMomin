package com.beingmomin.mominapp.di.builder

import com.beingmomin.mominapp.ui.ambassadorModule.ambassadorDescription.AmbassadorDescriptionActivity
import com.beingmomin.mominapp.ui.ambassadorModule.applyForAmbassador.ApplyForAmbassadorActivity
import com.beingmomin.mominapp.ui.ambassadorModule.localitySearch.LocalitySearchActivity
import com.beingmomin.mominapp.ui.ambassadorModule.localitySearch.LocalitySearchActivityModule
import com.beingmomin.mominapp.ui.ambassadorModule.main.MainActivity
import com.beingmomin.mominapp.ui.ambassadorModule.main.MainActivityModule
import com.beingmomin.mominapp.ui.ambassadorModule.showLocality.ShowLocalityActivity
import com.beingmomin.mominapp.ui.ambassadorModule.signIn.SignInActivity
import com.beingmomin.mominapp.ui.appModule.dashboard.DashboardActivity
import com.beingmomin.mominapp.ui.appModule.detailedPerson.DetailedPersonActivity
import com.beingmomin.mominapp.ui.appModule.familyHierarchy.FamilyHierarchyActivity
import com.beingmomin.mominapp.ui.appModule.splash.SplashActivity
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

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuilder::class))
    abstract fun bindDashboardActivity(): DashboardActivity

    @ContributesAndroidInjector
    abstract fun bindFamilyHierarchyActivity(): FamilyHierarchyActivity

    @ContributesAndroidInjector
    abstract fun bindDetailedPersonctivity(): DetailedPersonActivity

}