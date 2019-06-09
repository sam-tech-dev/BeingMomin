package com.beingmomin.mominapp.ui.appModule.dashboard.fragments.hierarchy

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.data.network.models.GetFamiliesBody
import com.beingmomin.mominapp.data.network.models.Locality
import com.beingmomin.mominapp.data.network.models.SearchPersonApiBody
import com.beingmomin.mominapp.ui.base.BaseViewModel
import com.beingmomin.mominapp.utils.AppConstants
import com.beingmomin.mominapp.utils.rx.SchedulerProvider

class HierarchyViewModel(val dataManager: DataManager, schedulerProvider: SchedulerProvider) : BaseViewModel<HierarchyNavigator>(schedulerProvider){

    var listOfLocality = MutableLiveData<MutableList<Locality>>()
    var listOfGenders = MutableLiveData<MutableList<String>>()
    var personAdapter = PersonAdapter()
    var familyAdapter = FamilyAdapter()
    val mIsPersonLoading = ObservableBoolean(false)
    val mIsFamiliesLoading = ObservableBoolean(false)
    val mIsFamiliesShow = ObservableBoolean(false)

    init {
        listOfGenders.value = mutableListOf("Male", "Female")
        getLocalities()
    }

    private fun getLocalities() {
        getCompositeDisposable().add(dataManager.doGetLocalitiesApiCall()
                .doOnSuccess({ response ->
                    Log.d(AppConstants.APP_TAG, response.toString())
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe({ response ->
                    listOfLocality.value = response.localities.sortedWith(compareBy({ it.localityName})).toMutableList()

                }, { throwable ->
                    Log.d(AppConstants.APP_TAG, throwable.toString())
                }))
    }



   fun searchPerson(request: SearchPersonApiBody) {
       mIsPersonLoading.set(true)
        getCompositeDisposable().add(dataManager.doSearchPersonApiCall(request)
                .doOnSuccess({ response ->
                    Log.d(AppConstants.APP_TAG, response.toString())
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe({ response ->
                    mIsPersonLoading.set(false)
                    personAdapter.updatePersonList(response.persons)

                }, { throwable ->
                    mIsPersonLoading.set(false)
                    Log.d(AppConstants.APP_TAG, throwable.toString())
                }))
    }


    fun getFamilies(request: GetFamiliesBody) {
        mIsFamiliesLoading.set(true)
        getCompositeDisposable().add(dataManager.doGetFamiliesApiCall(request)
                .doOnSuccess({ response ->
                    Log.d(AppConstants.APP_TAG, response.toString())
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe({ response ->
                    mIsFamiliesLoading.set(false)
                    mIsFamiliesShow.set(true)
                    familyAdapter.updateAncestorList(response.ancestors)

                }, { throwable ->
                    mIsFamiliesLoading.set(false)
                    Log.d(AppConstants.APP_TAG, throwable.toString())
                }))
    }



    private fun onNavBackClick() {
        getNavigator()?.goBack()
    }
}
