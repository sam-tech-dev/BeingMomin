package com.beingmomin.mominapp.ui.appModule.detailedPerson

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.data.network.models.GetDetailedPersonBody
import com.beingmomin.mominapp.data.network.models.PersonData
import com.beingmomin.mominapp.ui.base.BaseViewModel
import com.beingmomin.mominapp.utils.rx.SchedulerProvider


class DetailedPersonViewModel constructor(val dataManager: DataManager, schedulerProvider: SchedulerProvider) : BaseViewModel<DetailedPersonNavigator>(schedulerProvider) {

    var primaryDetails = MutableLiveData<String>()
    var nameOfPerson = MutableLiveData<String>()
    var profileUrl = MutableLiveData<String>()
    var extraDetailedAdapter = ExtraDetailAdapter()


    init {

    }

    fun loadDetailedPerson(bundle: Bundle) {
        setIsLoading(true)
        getCompositeDisposable().add(dataManager.doGetDetailedPersonApiCall(GetDetailedPersonBody(bundle.getInt("personId")))
                .doOnSuccess({ response ->
                    log(response.toString())
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe({ response ->
                    setIsLoading(false)
                    if (response.status == 0) {
                        setLayoutView(response.data)
                    } else {
                        //TODO handle error
                    }
                }, { throwable ->
                    setIsLoading(false)
                    log(throwable.toString())
                    getNavigator()!!.onHandleError(throwable)
                }))
    }

    private fun setLayoutView(personData: PersonData) {
        nameOfPerson.value = personData.name
        profileUrl.value = personData.profilePicUrl
        var primary = personData.gender
        if (personData.maritalStatus != null && personData.maritalStatus.isNotEmpty()) {
            primary = "$primary | $personData.maritalStatus"
        }
        if (personData.profession != null && personData.profession.isNotEmpty()) {
            primary = "$primary | ${personData.profession}"
        }
        if (personData.isAlive) {
            primary = "$primary | Alive"
        } else {
            primary = "$primary | No more"
        }
        primaryDetails.value= primary
        extraDetailedAdapter.updateExtraDetailList(personData.extraDetails)


    }

}