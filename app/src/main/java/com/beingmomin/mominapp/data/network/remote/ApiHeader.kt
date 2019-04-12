package com.beingmomin.mominapp.data.network.remote

import com.beingmomin.mominapp.di.annotations.Token
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
data class ApiHeader @Inject constructor(
        @Token
        @field:Expose
        @field:SerializedName("Authorization")
        var apiToken: String

)
