package com.beingmomin.mominapp.ui.appModule.detailedPerson

import android.os.Bundle
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.data.network.models.GetDetailedPersonBody
import com.beingmomin.mominapp.data.network.models.PersonData
import com.beingmomin.mominapp.ui.base.BaseViewModel
import com.beingmomin.mominapp.utils.rx.SchedulerProvider


class DetailedPersonViewModel constructor(val dataManager: DataManager, schedulerProvider: SchedulerProvider) : BaseViewModel<DetailedPersonNavigator>(schedulerProvider) {

    var primaryDetails = MutableLiveData<String>()
    var nameOfPerson = MutableLiveData<String>()
    var educationKey = MutableLiveData<String>()
    var educationDetails = MutableLiveData<String>()
    var profilePicUrl = MutableLiveData<String>()
    var extraDetailedAdapter = ExtraDetailAdapter()
    var familyGroupAdapter = FamilyGroupAdapter()

    val mIsEducationKeyAvailable = ObservableBoolean(false)
    val mIsEducationDetailsAvailable = ObservableBoolean(false)

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
                        //
                    }
                }, { throwable ->
                    setIsLoading(false)
                    log(throwable.toString())
                    getNavigator()!!.onHandleError(throwable)
                }))
    }

    private fun setLayoutView(personData: PersonData) {

        nameOfPerson.value = personData.name
        if (personData.profilePicUrl != null && personData.profilePicUrl.isNotEmpty()) {
            profilePicUrl.value = personData.profilePicUrl
        } else {
            getNavigator()!!.setProfilePlaceholder(personData.gender)
        }

        var primary = personData.gender
        if (personData.maritalStatus != null && personData.maritalStatus.isNotEmpty()) {
            primary = "$primary | ${personData.maritalStatus}"
        }
        if (personData.profession != null && personData.profession.isNotEmpty()) {
            primary = "$primary | ${personData.profession}"
        }
        if (personData.isAlive) {
            primary = "$primary | Alive"
        } else {
            primary = "$primary | No more"
        }

        primaryDetails.value = primary
        extraDetailedAdapter.updateExtraDetailList(personData.extraDetails)
        familyGroupAdapter.updateFamilyGroupsList(personData.family)
        if (personData.educationKey != null && personData.educationKey.isNotEmpty()) {
            educationKey.value = personData.educationKey
            mIsEducationKeyAvailable.set(true)
        }

        if (personData.educationDeatils != null && personData.educationDeatils.isNotEmpty()) {
            mIsEducationDetailsAvailable.set(true)
            educationDetails.value = personData.educationDeatils
        }

    }

}