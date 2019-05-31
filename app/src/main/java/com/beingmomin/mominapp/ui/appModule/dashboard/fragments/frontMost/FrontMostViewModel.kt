package com.beingmomin.mominapp.ui.appModule.dashboard.fragments.frontMost

import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.ui.base.BaseViewModel
import com.beingmomin.mominapp.utils.rx.SchedulerProvider

class FrontMostViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) : BaseViewModel<FrontMostNavigator>(schedulerProvider) {

    fun onNavBackClick() {
        getNavigator()?.goBack()
    }
}
