package com.beingmomin.mominapp.di.builder

import com.beingmomin.mominapp.ui.ambassadorModule.main.fragments.addPerson.AddPersonFragment
import com.beingmomin.mominapp.ui.ambassadorModule.main.fragments.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun bindAddPersonFragment(): AddPersonFragment

}