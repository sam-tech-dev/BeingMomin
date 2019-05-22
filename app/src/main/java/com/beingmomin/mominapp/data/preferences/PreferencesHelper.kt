package com.beingmomin.mominapp.data.preferences

/**
 * Created by sam on 7/4/18.
 */
interface PreferencesHelper {

    fun setAccessToken(accessToken: String)
    fun getAccessToken(): String

    fun setUsername(username: String)
    fun getUsername(): String


    fun setIsLoginFlag(flag: Boolean)
    fun getIsLogin(): Boolean


    fun setLocality(locality: String)
    fun getLocality(): String

    fun clearAppPreferences()

}