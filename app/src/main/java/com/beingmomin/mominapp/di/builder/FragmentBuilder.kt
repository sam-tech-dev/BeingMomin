package com.beingmomin.mominapp.di.builder

import com.beingmomin.mominapp.ui.ambassadorModule.main.fragments.addPerson.AddPersonFragment
import com.beingmomin.mominapp.ui.ambassadorModule.main.fragments.home.HomeFragment
import com.beingmomin.mominapp.ui.appModule.dashboard.fragments.frontMost.FrontMostFragment
import com.beingmomin.mominapp.ui.appModule.dashboard.fragments.hierarchy.HierarchyFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun bindAddPersonFragment(): AddPersonFragment

    @ContributesAndroidInjector
    abstract fun bindFrontMostFragment(): FrontMostFragment

    @ContributesAndroidInjector
    abstract fun bindHierarchyFragment(): HierarchyFragment

}