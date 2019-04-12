package com.beingmomin.mominapp.ui.ambassadorDescription

import com.beingmomin.mominapp.utils.rx.SchedulerProvider
import com.beingmomin.mominapp.ui.base.BaseViewModel


class AmbassadorDescriptionViewModel constructor(schedulerProvider: SchedulerProvider): BaseViewModel<AmbassadorDescriptionNavigator>(schedulerProvider) {

    fun openSearchLocalityActivity(){
        getNavigator()!!.openSearchLocalityActivity()
    }

}
