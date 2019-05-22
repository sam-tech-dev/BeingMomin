package com.beingmomin.mominapp.ui.ambassadorModule.main

import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.ui.base.BaseViewModel
import com.beingmomin.mominapp.utils.rx.SchedulerProvider


class MainViewModel constructor(val dataManager: DataManager, schedulerProvider: SchedulerProvider) : BaseViewModel<MainNavigator>(schedulerProvider) {


     fun processLogOut(){
         dataManager.clearAppPreferences()
         getNavigator()!!.openSignInScreen()
     }

}