package com.beingmomin.mominapp.ui.localitySearch

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import android.view.View
import com.beingmomin.mominapp.data.network.models.Ambassador
import com.beingmomin.mominapp.ui.showLocality.ShowLocalityActivity
import com.google.gson.Gson

class LocalitySearchItemViewModel : ViewModel(), View.OnClickListener  {

    var localityName = MutableLiveData<String>()
    var localityAddress = MutableLiveData<String>()
    var name = MutableLiveData<String>()
    var mobileNumber = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var image = MutableLiveData<String>()
    var ambassadorObj: Ambassador?=null


    fun bind(ambassador: Ambassador) {

        ambassadorObj=ambassador
        Log.d("az", "key " + ambassador.localityKey)
        localityName.value = ambassador.localityKey
        localityAddress.value = ambassador.tahsil + ", " + ambassador.district
        name.value = ambassador.name
        mobileNumber.value = ambassador.mobileNo
        email.value = ambassador.email
    }


    override fun onClick(v: View?) {
        Log.d("azz",localityName.value+"clicked")
        val gson= Gson()
        val intent=Intent(v?.context,ShowLocalityActivity::class.java)
        intent.putExtra("Ambassador",gson.toJson(ambassadorObj))
        v?.context?.startActivity(intent)
    }

//    @BindingAdapter("bind:imageUrl")
//    fun loadImage(view: CircleImageView, imageUrl: String) {
//
//
//        Glide.with(context)
//                .load("http://via.placeholder.com/300.png")
//                .into(ivImg);
//
//
//    }

}