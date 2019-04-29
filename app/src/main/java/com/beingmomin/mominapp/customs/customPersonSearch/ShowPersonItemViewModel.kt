package com.beingmomin.mominapp.customs.customPersonSearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beingmomin.mominapp.data.network.models.Person

class ShowPersonItemViewModel : ViewModel() {

    var personWithFather = MutableLiveData<String>()
    var pId = MutableLiveData<Int>()
    var locality= MutableLiveData<String>()


    fun bind(person: Person ) {
        personWithFather.value = person.name+" s/o "+person.father
        locality.value = "from : "+person.locality
        pId.value= person.id
    }

}