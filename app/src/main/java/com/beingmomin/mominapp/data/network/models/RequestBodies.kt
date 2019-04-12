package com.beingmomin.mominapp.data.network.models

import com.google.gson.annotations.SerializedName

data class LoginApiBody(
        @SerializedName("Username")var username:String,
        @SerializedName("Password")var password:String
)

data class SignUpApiBody(
		@SerializedName("mobileNumber") var mobileNumber: String,
		@SerializedName("email") var email: String,
		@SerializedName("fullName") var fullName: String,
		@SerializedName("qualification") var qualification: String,
		@SerializedName("address") var address: String
)