package com.beingmomin.mominapp.ui.applyForAmbassador

interface ApplyForAmbassadorNavigator {

    fun handleError(throwable: Throwable)

    fun showMessage(msg:String)

    fun pressBack()

}