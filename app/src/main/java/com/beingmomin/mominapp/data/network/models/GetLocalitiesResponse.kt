package com.beingmomin.mominapp.data.network.models
import com.google.gson.annotations.SerializedName


data class GetLocalitiesResponse(
    @SerializedName("localities")
    val localities: List<Locality>,
    @SerializedName("status")
    val status: Int
)

data class Locality(@SerializedName("localityId") val localityId: Int,
    @SerializedName("localityName") val localityName: String
){
    override fun toString(): String {
        return localityName
    }
}