package com.beingmomin.mominapp.data.network.models
import com.google.gson.annotations.SerializedName



data class LocalityAmbassadorsResponse(
		@SerializedName("Status") var status: Int,
		@SerializedName("ambassadors") var ambassadors: MutableList<Ambassador>
)

data class Ambassador(
		@SerializedName("state") var state: String,
		@SerializedName("profilePic") var profilePic: String,
		@SerializedName("name") var name: String,
		@SerializedName("district") var district: String,
		@SerializedName("localityKey") var localityKey: String,
		@SerializedName("tahsil") var tahsil: String,
		@SerializedName("mobileNo") var mobileNo: String,
		@SerializedName("email") var email: String,
		@SerializedName("address") var address: String
)