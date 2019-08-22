package com.beingmomin.mominapp.ui.ambassadorModule.main.fragments.addNews

import android.util.Log
import com.beingmomin.mominapp.customs.customPersonSearch.CustomPersonSearchEditText
import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.data.network.models.AddNewsApiBody
import com.beingmomin.mominapp.data.network.models.Locality
import com.beingmomin.mominapp.ui.base.BaseViewModel
import com.beingmomin.mominapp.utils.rx.SchedulerProvider
import java.io.File

class AddNewsViewModel(val dataManager: DataManager, schedulerProvider: SchedulerProvider) : BaseViewModel<AddNewsNavigator>(schedulerProvider) {

    //var firstName = MutableLiveData<String>()
    //var lastName = MutableLiveData<String>()

    init {
    }


    fun requestToAddNews(requestBody: AddNewsApiBody, newsAttachmentFile: File?) {
        setIsLoading(true)
        requestBody.localityId= dataManager.getLocalityId().toString()
        requestBody.userId= dataManager.getUserId().toString()
        getCompositeDisposable().add(dataManager.doAddNewsApiCall(requestBody, newsAttachmentFile)
                .doOnSuccess({ response ->
                    log(response.toString())
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe({ response ->
                    setIsLoading(false)
                    Log.d("az", "news push status :" + response.status)

                }, { throwable ->
                    setIsLoading(false)
                    log(throwable.toString())
                    getNavigator()!!.handleError(throwable)
                }))
    }

    fun setLocalityInSearchDialog(personSearch:CustomPersonSearchEditText) {
      personSearch.localityObject= Locality(dataManager.getLocalityId(),dataManager.getLocality())
    }

    fun onNavBackClick() {
        getNavigator()?.goBack()
    }

}
