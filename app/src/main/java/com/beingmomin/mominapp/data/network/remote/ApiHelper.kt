package com.beingmomin.mominapp.data.network.remote

import com.beingmomin.mominapp.data.network.models.*
import io.reactivex.Single
import java.io.File


interface ApiHelper {

    fun doLoginApiCall(request: LoginApiBody): Single<SignInResponse>

    fun doSignUpApiCall(request: SignUpApiBody): Single<SignUpResponse>

    fun doLocalityAmbassadorApiCall(): Single<LocalityAmbassadorsResponse>

    fun doSearchPersonApiCall(request : SearchPersonApiBody): Single<SearchPersonResponse>

    fun doAddPersonApiCall(request:AddPersonApiBody,profileFile: File):Single<AddPersonResponse>

    fun doGetLocalitiesApiCall(): Single<GetLocalitiesResponse>
}
