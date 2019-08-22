package com.beingmomin.mominapp.ui.ambassadorModule.main.fragments.addPerson

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.data.network.models.AddPersonApiBody
import com.beingmomin.mominapp.ui.base.BaseViewModel
import com.beingmomin.mominapp.utils.CommonUtils
import com.beingmomin.mominapp.utils.rx.SchedulerProvider
import java.io.File

class AddPersonViewModel(val dataManager: DataManager, schedulerProvider: SchedulerProvider) : BaseViewModel<AddPersonNavigator>(schedulerProvider) {

    var firstName = MutableLiveData<String>()
    var lastName = MutableLiveData<String>()
    var mobileNumber = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var qualification = MutableLiveData<String>()
    var address = MutableLiveData<String>()


    init {

        firstName.value = ""
        lastName.value = ""
        mobileNumber.value = ""
        email.value = ""
        qualification.value = ""
        address.value = ""

    }


    fun requestToAddPeople(requestBody: AddPersonApiBody, profileFile: File, ext: String) {
        setIsLoading(true)
        requestBody.profileName = getProfileName(ext)
        getCompositeDisposable().add(dataManager.doAddPersonApiCall(requestBody, profileFile)
                .doOnSuccess({ response ->
                    log(response.toString())
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe({ response ->
                    setIsLoading(false)
                    Log.d("az", "people push status :" + response.status)

                }, { throwable ->
                    setIsLoading(false)
                    log(throwable.toString())
                    getNavigator()!!.handleError(throwable)
                }))
    }


    fun onNavBackClick() {
        getNavigator()?.goBack()
    }

    fun setLocality() {
        getNavigator()!!.setLocality(dataManager.getLocality(), dataManager.getLocalityId())
    }

    fun getProfileName(ext: String): String {
        return "Profile_" + dataManager.getUsername() + "_" + CommonUtils.timestamp + "." + ext
    }


}
