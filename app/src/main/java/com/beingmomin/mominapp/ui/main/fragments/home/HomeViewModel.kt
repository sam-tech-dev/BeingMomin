package com.beingmomin.mominapp.ui.main.fragments.home

import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.ui.base.BaseViewModel
import com.beingmomin.mominapp.utils.rx.SchedulerProvider

class HomeViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) : BaseViewModel<HomeNavigator>(schedulerProvider) {

    fun onNavBackClick() {
        getNavigator()?.goBack()
    }
}
