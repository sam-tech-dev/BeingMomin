package com.beingmomin.mominapp.ui.localitySearch

import com.beingmomin.mominapp.utils.rx.SchedulerProvider
import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.data.network.models.Ambassador
import com.beingmomin.mominapp.ui.base.BaseViewModel


class LocalitySearchViewModel constructor(var dataManager: DataManager,schedulerProvider: SchedulerProvider): BaseViewModel<LocalitySearchNavigator>(schedulerProvider) {

    var localitySearchAdapter = LocalitySearchAdapter()
    lateinit var listOfLocalities : List<Ambassador>

    init {
        loadLocalities()
    }

    fun loadLocalities() {
        setIsLoading(true)
        getCompositeDisposable().add(dataManager.doLocalityAmbassadorApiCall()
                .doOnSuccess({ response ->
                    log(response.toString())
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe({ response ->
                    setIsLoading(false)
                   // openMainActivity()
                    listOfLocalities=response.ambassadors
                    localitySearchAdapter.updateLocalityList(response.ambassadors)

                }, { throwable ->
                    setIsLoading(false)
                    log(throwable.toString())
                    getNavigator()!!.handleError(throwable)
                }))
    }



    fun filterList(str : String){

      val filteredList= mutableListOf<Ambassador>()

        for ( ambassador in listOfLocalities ){
            if(ambassador.localityKey.contains(str,true)){
                filteredList.add(ambassador)
            }
        }
        localitySearchAdapter.updateLocalityList(filteredList)

    }


}
