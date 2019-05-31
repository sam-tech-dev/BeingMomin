package com.beingmomin.mominapp.data.network.models

import com.google.gson.annotations.SerializedName


data class GetFamiliesResponse(
        @SerializedName("ancestors")
        val ancestors: List<Ancestor>,
        @SerializedName("status")
        val status: Int
)

data class Ancestor(
        @SerializedName("ancestorId")
        val ancestorId: Int,
        @SerializedName("ancestorName")
        val ancestorName: String,
        @SerializedName("familyCount")
        var familyCount: Int
)