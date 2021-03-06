package com.beingmomin.mominapp.data.network.remote

import com.beingmomin.mominapp.BuildConfig


object ApiEndPoint {

   const val ENDPOINT_LOG_IN = BuildConfig.BASE_URL + BuildConfig.API_EXT + "sign-in"

   const val ENDPOINT_SIGN_UP_AMBASSADOR = BuildConfig.BASE_URL + BuildConfig.API_EXT + "sign-up-ambassador"

   const val ENDPOINT_LOCALITY_AMBASSADORS = BuildConfig.BASE_URL + BuildConfig.API_EXT + "locality-ambassadors"

   const val ENDPOINT_SEARCH_PERSONS = BuildConfig.BASE_URL + BuildConfig.API_EXT + "search-person"

}
