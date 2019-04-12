package com.beingmomin.mominapp.ui.applyForAmbassador

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.utils.rx.SchedulerProvider
import com.beingmomin.mominapp.data.network.models.Ambassador
import com.beingmomin.mominapp.data.network.models.SignUpApiBody
import com.beingmomin.mominapp.ui.base.BaseViewModel
import com.google.gson.Gson


class ApplyForAmbassadorViewModel constructor(var dataManager: DataManager, schedulerProvider: SchedulerProvider) : BaseViewModel<ApplyForAmbassadorNavigator>(schedulerProvider) {

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


    fun submitAmbassadorForm(signUpApiBody: SignUpApiBody) {

        setIsLoading(true)
        getCompositeDisposable().add(dataManager.doSignUpApiCall(signUpApiBody)
                .doOnSuccess({ response ->
                    log(response.toString())
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe({ response ->
                    setIsLoading(false)
                    if (response.status == 0) {
                        getNavigator()!!.showMessage("Your response successfully recorded, " +
                                "you will be contacted by admin")
                    } else {
                        getNavigator()!!.showMessage("Some thing wrong with server")
                    }

                    getNavigator()?.pressBack()

                }, { throwable ->
                    setIsLoading(false)
                    log(throwable.toString())
                    getNavigator()!!.handleError(throwable)
                }))
    }


    fun clickToSubmit(){
        submitAmbassadorForm(SignUpApiBody(mobileNumber.value!!, email.value!!, firstName.value!! + " " + lastName.value!!,
                qualification.value!!, address.value!!))

    }


}