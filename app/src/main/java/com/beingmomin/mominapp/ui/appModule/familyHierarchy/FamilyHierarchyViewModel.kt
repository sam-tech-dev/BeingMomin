package com.beingmomin.mominapp.ui.appModule.familyHierarchy

import android.os.Bundle
import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.data.network.models.Children
import com.beingmomin.mominapp.data.network.models.GetFamilyHierarchyBody
import com.beingmomin.mominapp.ui.base.BaseViewModel
import com.beingmomin.mominapp.utils.rx.SchedulerProvider


class FamilyHierarchyViewModel constructor(val dataManager: DataManager, schedulerProvider: SchedulerProvider) : BaseViewModel<FamilyHierarchyNavigator>(schedulerProvider) {

    init {
    }

    fun loadFamilyHierarchy(bundle: Bundle) {
        setIsLoading(true)
        getCompositeDisposable().add(dataManager.doGetFamilyHierarchyApiCall(GetFamilyHierarchyBody(bundle.getInt("personId")))
                .doOnSuccess({ response ->
                    log(response.toString())
                })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe({ response ->
                    setIsLoading(false)
                    val childObj = calculateSpace(response.data)
                    getNavigator()!!.inflateHierarchy(null,childObj,0)
                }, { throwable ->
                    setIsLoading(false)
                    log(throwable.toString())
                    getNavigator()!!.onHandleError(throwable)
                }))
    }


    private fun calculateSpace(root: Children): Children {

        if (root.children!=null && root.children.size > 0) {
            for (child in root.children){
                 calculateSpace(child)
            }
            var sumOfSpace = 0
            for (child in root.children)
                sumOfSpace = sumOfSpace + child.spaceVal

            root.spaceVal = sumOfSpace

        } else {
            root.spaceVal = 1
        }

        return root
    }

}