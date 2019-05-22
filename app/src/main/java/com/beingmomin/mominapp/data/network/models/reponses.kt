package com.beingmomin.mominapp.data.network.models

import com.google.gson.annotations.SerializedName

data class AddPersonResponse(
        @SerializedName("Status") var status: Int
)


data class GetLocalitiesResponse(
        @SerializedName("Status") var status: Int,
        @SerializedName("Localities") var localities: MutableList<String>
)