package com.beingmomin.mominapp.data.preferences
import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by sam on 6/4/18.
 */
class AppPreferences @Inject constructor(var sharedPref: SharedPreferences){

    fun storePreferences(key:String, value: String){
        sharedPref.edit().putString(key,value).commit()
    }

    fun storePreferences(key:String, value: Float){
        sharedPref.edit().putFloat(key,value).commit()
    }

    fun storePreferences(key:String, value: Int){
        sharedPref.edit().putInt(key,value).commit()
    }

    fun storePreferences(key:String, value: Boolean){
        sharedPref.edit().putBoolean(key,value).commit()
    }

    fun storePreferences(key:String, value: Long){
        sharedPref.edit().putLong(key,value).commit()
    }


    fun fetchPreferences(key: String, defaultValue : String):String{
        return sharedPref.getString(key,defaultValue)
    }

    fun fetchPreferences(key: String, defaultValue : Int):Int{
        return sharedPref.getInt(key,defaultValue)
    }

    fun fetchPreferences(key: String, defaultValue : Boolean):Boolean{
        return sharedPref.getBoolean(key,defaultValue)
    }

    fun fetchPreferences(key: String, defaultValue : Float):Float{
        return sharedPref.getFloat(key,defaultValue)
    }

    fun fetchPreferences(key: String, defaultValue : Long):Long{
        return sharedPref.getLong(key,defaultValue)
    }

    fun deletePreferences(key:String){
        sharedPref.edit().remove(key).commit()
    }

    fun clearAllPreferences(){
        sharedPref.edit().clear().commit()
    }

}