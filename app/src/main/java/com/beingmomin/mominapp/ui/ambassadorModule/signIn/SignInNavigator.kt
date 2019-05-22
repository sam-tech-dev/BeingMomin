package com.beingmomin.mominapp.ui.ambassadorModule.signIn


interface SignInNavigator {

    fun handleError(throwable: Throwable)

    fun openMainActivity()

    fun signInToServer()

    fun openAmbassadorDescriptionActivity()

    fun showMessage(msg :String)
}
