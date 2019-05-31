package com.beingmomin.mominapp.utils

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beingmomin.mominapp.data.network.models.Ancestor

class ShowFamilyModel : ViewModel() {
    var familyCount = MutableLiveData<String>()
    var ancestorId = MutableLiveData<Int>()
    var ancestorName= MutableLiveData<String>()

    fun bind(ancestor: Ancestor) {
        familyCount.value = "Family :${ancestor.familyCount}"
        ancestorId.value = ancestor.ancestorId
        ancestorName.value= ancestor.ancestorName
    }
}