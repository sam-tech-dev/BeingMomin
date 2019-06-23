package com.beingmomin.mominapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.ui.ambassadorModule.ambassadorDescription.AmbassadorDescriptionViewModel
import com.beingmomin.mominapp.ui.ambassadorModule.applyForAmbassador.ApplyForAmbassadorViewModel
import com.beingmomin.mominapp.ui.ambassadorModule.localitySearch.LocalitySearchViewModel
import com.beingmomin.mominapp.ui.ambassadorModule.main.MainViewModel
import com.beingmomin.mominapp.ui.ambassadorModule.main.fragments.addPerson.AddPersonViewModel
import com.beingmomin.mominapp.ui.ambassadorModule.main.fragments.home.HomeViewModel
import com.beingmomin.mominapp.ui.ambassadorModule.showLocality.ShowLocalityViewModel
import com.beingmomin.mominapp.ui.ambassadorModule.signIn.SignInViewModel
import com.beingmomin.mominapp.ui.appModule.dashboard.DashboardViewModel
import com.beingmomin.mominapp.ui.appModule.dashboard.fragments.frontMost.FrontMostViewModel
import com.beingmomin.mominapp.ui.appModule.dashboard.fragments.hierarchy.HierarchyViewModel
import com.beingmomin.mominapp.ui.appModule.detailedPerson.DetailedPersonViewModel
import com.beingmomin.mominapp.ui.appModule.familyHierarchy.FamilyHierarchyViewModel
import com.beingmomin.mominapp.ui.appModule.splash.SplashViewModel
import com.beingmomin.mominapp.utils.rx.SchedulerProvider
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ViewModelProviderFactory @Inject
constructor(private val dataManager: DataManager,
            private val schedulerProvider: SchedulerProvider) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(dataManager, schedulerProvider) as T

        } else if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            return SignInViewModel(dataManager, schedulerProvider) as T

        } else if (modelClass.isAssignableFrom(ShowLocalityViewModel::class.java)) {
            return ShowLocalityViewModel(schedulerProvider) as T

        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return  MainViewModel(dataManager, schedulerProvider) as T

        }else if (modelClass.isAssignableFrom(LocalitySearchViewModel::class.java)) {
            return LocalitySearchViewModel(dataManager,schedulerProvider) as T

        }else if (modelClass.isAssignableFrom(ApplyForAmbassadorViewModel::class.java)) {
            return ApplyForAmbassadorViewModel(dataManager,schedulerProvider) as T

        }else if (modelClass.isAssignableFrom(AmbassadorDescriptionViewModel::class.java)) {
            return AmbassadorDescriptionViewModel(schedulerProvider) as T

        }else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(dataManager,schedulerProvider) as T

        }else if (modelClass.isAssignableFrom(AddPersonViewModel::class.java)) {
            return AddPersonViewModel(dataManager,schedulerProvider) as T

        }else if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(dataManager,schedulerProvider) as T

        }else if (modelClass.isAssignableFrom(FrontMostViewModel::class.java)) {
            return FrontMostViewModel(dataManager,schedulerProvider) as T

        }else if (modelClass.isAssignableFrom(HierarchyViewModel::class.java)) {
            return HierarchyViewModel(dataManager,schedulerProvider) as T

        }else if (modelClass.isAssignableFrom(FamilyHierarchyViewModel::class.java)) {
            return FamilyHierarchyViewModel(dataManager,schedulerProvider) as T

        }else if (modelClass.isAssignableFrom(DetailedPersonViewModel::class.java)) {
            return DetailedPersonViewModel(dataManager,schedulerProvider) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}