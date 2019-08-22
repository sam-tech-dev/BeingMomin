package com.beingmomin.mominapp.data.preferences

/**
 * Created by sam on 7/4/18.
 */
interface PreferencesHelper {

    fun setAccessToken(accessToken: String)
    fun getAccessToken(): String

    fun setUsername(username: String)
    fun getUsername(): String

    fun setUserId(userid: Int)
    fun getUserId(): Int


    fun setIsLoginFlag(flag: Boolean)
    fun getIsLogin(): Boolean


    fun setLocality(locality: String)
    fun getLocality(): String

    fun setLocalityId(localityid: Int)
    fun getLocalityId(): Int

    fun clearAppPreferences()

}