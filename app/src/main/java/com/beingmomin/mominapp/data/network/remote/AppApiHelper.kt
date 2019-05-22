package com.beingmomin.mominapp.data.network.remote

import com.beingmomin.mominapp.data.network.models.*
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppApiHelper @Inject constructor(val apiHeader: ApiHeader) : ApiHelper {

//    .addHeaders(apiHeader.apiToken)

    override fun doLoginApiCall(request: LoginApiBody): Single<SignInResponse> {

        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOG_IN)
                .addApplicationJsonBody(request)
                .build()
                .getObjectSingle<SignInResponse>(SignInResponse::class.java)
    }

    override fun doSignUpApiCall(request: SignUpApiBody): Single<SignUpResponse> {

        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SIGN_UP_AMBASSADOR)
                .addApplicationJsonBody(request)
                .build()
                .getObjectSingle<SignUpResponse>(SignUpResponse::class.java)
    }


    override fun doLocalityAmbassadorApiCall(): Single<LocalityAmbassadorsResponse> {

        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_LOCALITY_AMBASSADORS)
                .build()
                .getObjectSingle<LocalityAmbassadorsResponse>(LocalityAmbassadorsResponse::class.java)
    }

    override fun doSearchPersonApiCall(request: SearchPersonApiBody): Single<SearchPersonResponse> {

        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SEARCH_PERSONS)
                .addApplicationJsonBody(request)
                .build()
                .getObjectSingle<SearchPersonResponse>(SearchPersonResponse::class.java)
   }

    override fun doAddPersonApiCall(request: AddPersonApiBody, profileFile:File): Single<AddPersonResponse> {

        return Rx2AndroidNetworking.upload(ApiEndPoint.ENDPOINT_ADD_PERSONS)
                .addMultipartFile("profileFile",profileFile)
                .addMultipartParameter("fullName",request.fullName)
                .addMultipartParameter("mobileNumber",request.mobileNumber)
                .addMultipartParameter("dob",request.dob)
                .addMultipartParameter("email",request.email)
                .addMultipartParameter("gender",request.gender)
                .addMultipartParameter("maritalStatus",request.maritalStatus)
                .addMultipartParameter("lifePartnerId",request.lifeParterId)
                .addMultipartParameter("educationLevel",request.educationLevel)
                .addMultipartParameter("educationDetails",request.educationDetails)
                .addMultipartParameter("localityKey",request.localityKey)
                .addMultipartParameter("homeAddress",request.homeAddress)
                .addMultipartParameter("fatherId",request.fatherId)
                .addMultipartParameter("motherId",request.motherId)
                .addMultipartParameter("aliveFlag",request.aliveFlag)
                .addMultipartParameter("profession",request.profession)
                .addMultipartParameter("profileName",request.profileName)
                .build().getObjectSingle<AddPersonResponse>(AddPersonResponse::class.java)
    }


    override fun doGetLocalitiesApiCall(): Single<GetLocalitiesResponse> {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GET_LOCALITIES)
                .build()
                .getObjectSingle<GetLocalitiesResponse>(GetLocalitiesResponse::class.java)

    }
}
