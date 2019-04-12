package com.beingmomin.mominapp.data

import com.beingmomin.mominapp.data.preferences.AppPreferencesHelper
import com.beingmomin.mominapp.data.network.models.LocalityAmbassadorsResponse
import com.beingmomin.mominapp.data.network.models.LoginApiBody
import com.beingmomin.mominapp.data.network.models.SignUpApiBody
import com.beingmomin.mominapp.data.network.models.SignUpResponse
import com.beingmomin.mominapp.data.network.remote.AppApiHelper
import io.reactivex.Single
import javax.inject.Inject


/**
 * Created by sam on 19/3/18.
 */
class DataManager @Inject constructor( private  val appPrefHelper: AppPreferencesHelper,
                                      private val apiHelper: AppApiHelper) : dtManager {


    /**
     * start of shared preferences functions
     */
    override fun setAccessToken(accessToken: String)=appPrefHelper.setAccessToken(accessToken)

    override fun getAccessToken(): String =appPrefHelper.getAccessToken()

    override fun setUsername(username: String) =appPrefHelper.setUsername(username)

    override fun getUsername(): String=appPrefHelper.getUsername()

    override fun setIsLoginFlag(flag: Boolean)=appPrefHelper.setIsLoginFlag(flag)

    override fun getIsLogin(): Boolean=appPrefHelper.getIsLogin()

    /**
     * end of shared preferences functions
     */

    /**
     * start of api call functions
     */

    override fun  doLoginApiCall(request: LoginApiBody)=apiHelper.doLoginApiCall(request)

    override fun doSignUpApiCall(request:  SignUpApiBody): Single<SignUpResponse> = apiHelper.doSignUpApiCall(request)

    override fun doLocalityAmbassadorApiCall(): Single<LocalityAmbassadorsResponse> = apiHelper.doLocalityAmbassadorApiCall()
    /**
     * end of api call functions
     */



}