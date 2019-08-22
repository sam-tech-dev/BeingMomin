package com.beingmomin.mominapp.data.network.models

import com.google.gson.annotations.SerializedName

data class AddPersonResponse(
        @SerializedName("Status") var status: Int
)

data class AddNewsResponse(
        @SerializedName("Status") var status: Int
)



