package com.beingmomin.mominapp.data.network.remote

import com.beingmomin.mominapp.data.network.models.*
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single
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
                .addHeaders(apiHeader.apiToken)
                .build()
                .getObjectSingle<SearchPersonResponse>(SearchPersonResponse::class.java)
   }



}
