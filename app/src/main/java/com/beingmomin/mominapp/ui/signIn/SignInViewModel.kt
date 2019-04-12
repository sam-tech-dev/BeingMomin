package com.beingmomin.mominapp.ui.signIn

import android.text.TextUtils
import com.beingmomin.mominapp.utils.CommonUtils
import com.beingmomin.mominapp.utils.rx.SchedulerProvider
import com.beingmomin.mominapp.data.DataManager
import com.beingmomin.mominapp.data.network.models.LoginApiBody
import com.beingmomin.mominapp.ui.base.BaseViewModel


class SignInViewModel constructor(var dataManager: DataManager,  schedulerProvider: SchedulerProvider): BaseViewModel<SignInNavigator>(schedulerProvider) {

    fun isEmailAndPasswordValid(username: String, password: String): Boolean {
        if (TextUtils.isEmpty(username)) {
            return false
        }
        if (!CommonUtils.isMobileNumberValid(username)) {
            return false
        }
        return !TextUtils.isEmpty(password)
    }

    fun openMainActivity() {
       getNavigator()!!.openMainActivity()
    }

    fun openAmbassadorDescriptionActivity() {
        getNavigator()!!.openAmbassadorDescriptionActivity()
    }


    fun login(email: String, password: String) {
        setIsLoading(true)
        getCompositeDisposable().add(dataManager.doLoginApiCall(LoginApiBody(email,password))
                .doOnSuccess({ response ->
                   log(response.toString())
                 })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe({ response ->
                    setIsLoading(false)
                    when(response.status){
                        0 -> {
                            dataManager.setAccessToken(response.token)
                            dataManager.setIsLoginFlag(true)
                            openMainActivity()
                        }
                        1 -> getNavigator()?.showMessage("Something wrong with server")
                        2 -> getNavigator()?.showMessage("Please enter correct username or password")
                        3 -> getNavigator()?.showMessage("Admin haven't respond on your ambassador request yet.")
                    }
                }, { throwable ->
                    setIsLoading(false)
                    log(throwable.toString())
                    getNavigator()!!.handleError(throwable)
                }))
    }


    fun loginToServer(){
        getNavigator()!!.signInToServer()
    }

}
