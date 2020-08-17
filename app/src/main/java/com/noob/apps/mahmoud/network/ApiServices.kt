package com.noob.apps.mahmoud.network

import com.noob.apps.mahmoud.models.Country
import com.noob.apps.mahmoud.models.HomeResponse
import com.noob.apps.mahmoud.models.ProfileResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("all")
    fun getCountries() : Call<List<Country>>

    @GET("home")
    fun getHome() : Call<HomeResponse>

    @GET("profile")
    fun getProfile() : Call<ProfileResponse>

}