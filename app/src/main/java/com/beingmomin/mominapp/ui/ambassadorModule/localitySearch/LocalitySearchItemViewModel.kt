package com.beingmomin.mominapp.ui.ambassadorModule.localitySearch

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beingmomin.mominapp.data.network.models.Ambassador
import com.beingmomin.mominapp.ui.ambassadorModule.showLocality.ShowLocalityActivity
import com.google.gson.Gson

class LocalitySearchItemViewModel : ViewModel(), View.OnClickListener  {

    var localityName = MutableLiveData<String>()
    var localityAddress = MutableLiveData<String>()
    var name = MutableLiveData<String>()
    var mobileNumber = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var profileUrl = MutableLiveData<String>()
    var ambassadorObj: Ambassador?=null


    fun bind(ambassador: Ambassador) {
        ambassadorObj=ambassador
        Log.d("az", "key " + ambassador.localityKey)
        localityName.value = ambassador.localityKey
        localityAddress.value = ambassador.tahsil + ", " + ambassador.district
        name.value = ambassador.name
        mobileNumber.value = ambassador.mobileNo
        email.value = ambassador.email
        profileUrl.value=ambassador.profilePic
    }


    override fun onClick(v: View?) {
        Log.d("azz",localityName.value+"clicked")
        val gson= Gson()
        val intent=Intent(v?.context,ShowLocalityActivity::class.java)
        intent.putExtra("Ambassador",gson.toJson(ambassadorObj))
        v?.context?.startActivity(intent)
    }



}