package com.beingmomin.mominapp.data.network.models
import com.google.gson.annotations.SerializedName



data class SearchPersonResponse(
        @SerializedName("Status") var status: Int,
        @SerializedName("persons") var persons: MutableList<Person>
)


data class Person(
        @SerializedName("locality") var locality: String,
        @SerializedName("father") var father: String,
        @SerializedName("name") var name: String,
        @SerializedName("id") var id: Int
)