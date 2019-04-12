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


}