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

data class SearchPersonApiBody(
		@SerializedName("searchName") var searchName: String,
		@SerializedName("localityId") var localityId: Int,
		@SerializedName("gender") var gender: String
)

data class AddPersonApiBody(
		@SerializedName("fullName") var fullName: String,
		@SerializedName("mobileNumber") var mobileNumber: String,
		@SerializedName("dob") var dob: String,
		@SerializedName("email") var email: String,
		@SerializedName("gender") var gender: String,
		@SerializedName("maritalStatus") var maritalStatus: String,
		@SerializedName("lifeParterId") var lifeParterId: String,
		@SerializedName("educationLevel") var educationLevel: String,
		@SerializedName("educationDetails") var educationDetails: String,
		@SerializedName("localityKey") var localityKey: String,
		@SerializedName("homeAddress") var homeAddress: String,
		@SerializedName("fatherId") var fatherId: String,
		@SerializedName("motherId") var motherId: String,
        @SerializedName("aliveFlag") var aliveFlag: String,
		@SerializedName("profession") var profession: String,
		@SerializedName("profileName") var profileName: String
)

data class GetFamiliesBody(
		@SerializedName("localityId")var localityId:Int
)

data class GetFamilyHierarchyBody(
		@SerializedName("personId")var personId:Int
)

data class GetDetailedPersonBody(
		@SerializedName("personId")var personId:Int
)

data class AddNewsApiBody(
		@SerializedName("newsTitle") var newsTitle: String,
		@SerializedName("newsDescription") var newsDescription: String,
		@SerializedName("newsDate") var newsDate: String,
		@SerializedName("newsCategory") var newsCategory: String,
		@SerializedName("taggedPersons") var taggedPersons: String,
		@SerializedName("userId") var userId: String,
		@SerializedName("localityId") var localityId: String

)


