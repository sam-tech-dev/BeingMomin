package com.beingmomin.mominapp.ui.appModule.dashboard

import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.ui.base.BaseViewModel
import com.beingmomin.mominapp.utils.rx.SchedulerProvider


class DashboardViewModel constructor(val dataManager: DataManager, schedulerProvider: SchedulerProvider) : BaseViewModel<DashboardNavigator>(schedulerProvider) {

    init {

    }
}