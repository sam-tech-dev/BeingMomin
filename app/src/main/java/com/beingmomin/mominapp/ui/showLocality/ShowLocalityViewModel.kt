package com.beingmomin.mominapp.ui.showLocality

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.beingmomin.mominapp.utils.rx.SchedulerProvider
import com.beingmomin.mominapp.data.network.models.Ambassador
import com.beingmomin.mominapp.ui.base.BaseViewModel
import com.google.gson.Gson


class ShowLocalityViewModel constructor(schedulerProvider: SchedulerProvider) : BaseViewModel<ShowLocalityNavigator>(schedulerProvider) {

    var localityName = MutableLiveData<String>()
    var localityAddress = MutableLiveData<String>()

    var ambasaadorName = MutableLiveData<String>()
    var mobileNumber = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var address = MutableLiveData<String>()
    var imageUrl = MutableLiveData<String>()

    init {

    }

    fun ShowData(bundle: Bundle?) {
        val ambassador = Gson().fromJson(bundle?.getString("Ambassador"), Ambassador::class.java)
        localityName.value = ambassador.localityKey
        localityAddress.value = ambassador.tahsil + ", " + ambassador.district + ", " + ambassador.state;
        ambasaadorName.value = ambassador.name
        mobileNumber.value = ambassador.mobileNo
        email.value = ambassador.email
        address.value = ambassador.address


        log("amb " + ambassador.name)
    }


}