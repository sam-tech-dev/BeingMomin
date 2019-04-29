package com.beingmomin.mominapp.ui.main.fragments.addPerson

import androidx.lifecycle.MutableLiveData
import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.ui.base.BaseViewModel
import com.beingmomin.mominapp.utils.rx.SchedulerProvider

class AddPersonViewModel(val dataManager: DataManager, schedulerProvider: SchedulerProvider) : BaseViewModel<AddPersonNavigator>(schedulerProvider) {


    var firstName = MutableLiveData<String>()
    var lastName = MutableLiveData<String>()
    var mobileNumber = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var qualification = MutableLiveData<String>()
    var address = MutableLiveData<String>()


    init {
        firstName.value=""
        lastName.value=""
        mobileNumber.value=""
        email.value=""
        qualification.value=""
        address.value=""

    }


    fun onNavBackClick() {
        getNavigator()?.goBack()
    }


    fun setLocality(){
        getNavigator()!!.setLocality(dataManager.getLocality())
    }

}
