package com.beingmomin.mominapp.ui.ambassadorModule.main.fragments.addPerson


interface AddPersonNavigator {

    fun goBack()

    fun setLocality(locality:String, localityId: Int)

    fun handleError(throwable: Throwable)
}
