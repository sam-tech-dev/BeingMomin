package com.beingmomin.mominapp.data.preferences


import javax.inject.Inject

/**
 * Created by sam on 7/4/18.
 */
class AppPreferencesHelper @Inject constructor(var appPref: AppPreferences) : PreferencesHelper {

    companion object {
        const val PREF_KEY_ACCESS_TOKEN = "token"
        const val PREF_KEY_IS_LOGIN = "isLogin"
        const val PREF_KEY_USER_NAME = "username"
        const val PREF_KEY_USER_ID = "userId"
        const val PREF_KEY_LOCALITY = "localityId"
        const val PREF_KEY_LOCALITY_ID = "localityId"
    }

    override fun setAccessToken(accessToken: String) = appPref.storePreferences(PREF_KEY_ACCESS_TOKEN, accessToken)

    override fun getAccessToken(): String = appPref.fetchPreferences(PREF_KEY_ACCESS_TOKEN, "")

    override fun setUsername(username: String) {
        appPref.storePreferences(PREF_KEY_USER_NAME, username)
    }

    override fun getUsername(): String {
        return appPref.fetchPreferences(PREF_KEY_USER_NAME, "")
    }

    override fun setIsLoginFlag(flag: Boolean) {
        appPref.storePreferences(PREF_KEY_IS_LOGIN, flag)
    }

    override fun getIsLogin(): Boolean {
        return appPref.fetchPreferences(PREF_KEY_IS_LOGIN, false)
    }

    override fun setLocality(locality: String) {
        appPref.storePreferences(PREF_KEY_LOCALITY, locality)
    }

    override fun getLocality(): String {
        return appPref.fetchPreferences(PREF_KEY_LOCALITY, "")
    }

    override fun setUserId(userid: Int) {
        appPref.storePreferences(PREF_KEY_USER_ID, userid)
    }

    override fun getUserId(): Int {
        return appPref.fetchPreferences(PREF_KEY_USER_ID,0)
    }

    override fun setLocalityId(localityid: Int) {
        appPref.storePreferences(PREF_KEY_LOCALITY_ID, localityid)
    }

    override fun getLocalityId(): Int {
        return appPref.fetchPreferences(PREF_KEY_LOCALITY_ID,0)
    }

    override fun clearAppPreferences() = appPref.clearAllPreferences()



}