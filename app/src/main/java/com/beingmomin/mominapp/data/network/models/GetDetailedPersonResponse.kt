package com.beingmomin.mominapp.data.network.models
import com.google.gson.annotations.SerializedName


data class GetDetailedPersonResponse(
    @SerializedName("data")
    val `data`: PersonData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)

data class PersonData(
    @SerializedName("educationDeatils")
    val educationDeatils: String?,
    @SerializedName("educationKey")
    val educationKey: String?,
    @SerializedName("extraDetails")
    val extraDetails: List<ExtraDetail>,
    @SerializedName("family")
    val family: List<Family>,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("isAlive")
    val isAlive: Boolean,
    @SerializedName("maritalStatus")
    val maritalStatus: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("profession")
    val profession: String?,
    @SerializedName("profilePicUrl")
    val profilePicUrl: String?
)

data class Family(
    @SerializedName("group")
    val group: String,
    @SerializedName("members")
    val members: List<Member>,
    @SerializedName("order")
    val order: String
)

data class Member(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("relation")
    val relation: String
)

data class ExtraDetail(
    @SerializedName("info")
    val info: String,
    @SerializedName("order")
    val order: Int
)