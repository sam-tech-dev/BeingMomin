package com.beingmomin.mominapp.data

import com.beingmomin.mominapp.data.network.models.*
import com.beingmomin.mominapp.data.network.remote.AppApiHelper
import com.beingmomin.mominapp.data.preferences.AppPreferencesHelper
import io.reactivex.Single
import java.io.File
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

    override fun setLocality(locality: String) = appPrefHelper.setLocality(locality)

    override fun getLocality(): String = appPrefHelper.getLocality()

    override fun clearAppPreferences() = appPrefHelper.clearAppPreferences()

    override fun setUserId(userid: Int) = appPrefHelper.setUserId(userid)

    override fun getUserId(): Int =appPrefHelper.getUserId()

    override fun setLocalityId(localityid: Int)= appPrefHelper.setLocalityId(localityid)

    override fun getLocalityId(): Int = appPrefHelper.getLocalityId()

    /**
     * end of shared preferences functions
     */

    /**
     * start of api call functions
     */

    override fun  doLoginApiCall(request: LoginApiBody)=apiHelper.doLoginApiCall(request)

    override fun doSignUpApiCall(request:  SignUpApiBody): Single<SignUpResponse> = apiHelper.doSignUpApiCall(request)

    override fun doLocalityAmbassadorApiCall(): Single<LocalityAmbassadorsResponse> = apiHelper.doLocalityAmbassadorApiCall()

    override fun doSearchPersonApiCall(request: SearchPersonApiBody): Single<SearchPersonResponse> = apiHelper.doSearchPersonApiCall(request)

    override fun doAddPersonApiCall(request: AddPersonApiBody, profileFile: File): Single<AddPersonResponse> = apiHelper.doAddPersonApiCall(request,profileFile)

    override fun doGetLocalitiesApiCall(): Single<GetLocalitiesResponse> = apiHelper.doGetLocalitiesApiCall()

    override fun doGetFamiliesApiCall(request: GetFamiliesBody): Single<GetFamiliesResponse> = apiHelper.doGetFamiliesApiCall(request)

    override fun doGetFamilyHierarchyApiCall(request: GetFamilyHierarchyBody): Single<GetFamilyHierarchyResponse> = apiHelper.doGetFamilyHierarchyApiCall(request)

    override fun doGetDetailedPersonApiCall(request: GetDetailedPersonBody): Single<GetDetailedPersonResponse> = apiHelper.doGetDetailedPersonApiCall(request)

    override fun doAddNewsApiCall(request: AddNewsApiBody, newsAttachment: File?): Single<AddNewsResponse> = apiHelper.doAddNewsApiCall(request,newsAttachment)

    /**
     * end of api call functions
     */



}