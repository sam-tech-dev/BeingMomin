package com.beingmomin.mominapp.customs.customPersonSearch

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beingmomin.mominapp.data.network.models.Person

class ShowPersonItemViewModel : ViewModel(), View.OnClickListener  {

    var personWithFather = MutableLiveData<String>()
    var pId = MutableLiveData<Int>()
    var locality= MutableLiveData<String>()



    fun bind(person: Person ) {
        personWithFather.value = person.name+" s/o "+person.father
        locality.value = "from : $person.locality"
        pId.value= person.id
    }


    override fun onClick(v: View?) {
       /* Log.d("azz",localityName.value+"clicked")
        val gson= Gson()
        val intent=Intent(v?.context,ShowLocalityActivity::class.java)
        intent.putExtra("Ambassador",gson.toJson(ambassadorObj))
        v?.context?.startActivity(intent)*/
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