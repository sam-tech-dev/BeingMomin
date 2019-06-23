package com.beingmomin.mominapp.ui.appModule.familyHierarchy

import com.beingmomin.mominapp.data.network.models.Children

interface FamilyHierarchyNavigator {


    fun inflateHierarchy(root:Children?,child:Children, index:Int)

    fun onHandleError(throwable: Throwable)

}