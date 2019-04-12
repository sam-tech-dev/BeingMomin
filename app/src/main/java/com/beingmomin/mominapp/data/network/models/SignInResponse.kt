package com.beingmomin.mominapp.data.network.models
import com.google.gson.annotations.SerializedName



data class SignInResponse(
		@SerializedName("Status") var status: Int,
		@SerializedName("Username") var username: String,
		@SerializedName("Token") var token: String,
		@SerializedName("Locality") var locality: String
)


data class SignUpResponse(
		@SerializedName("Status") var status: Int
)