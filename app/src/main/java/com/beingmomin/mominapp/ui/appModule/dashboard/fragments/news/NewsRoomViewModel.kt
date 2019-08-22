package com.beingmomin.mominapp.ui.appModule.dashboard.fragments.news

import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.ui.base.BaseViewModel
import com.beingmomin.mominapp.utils.rx.SchedulerProvider

class NewsRoomViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) : BaseViewModel<NewsRoomNavigator>(schedulerProvider) {

    fun onNavBackClick() {
        getNavigator()?.goBack()
    }
}
