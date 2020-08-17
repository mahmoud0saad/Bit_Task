package com.noob.apps.mahmoudsaad.network

import com.noob.apps.mahmoudsaad.models.Country
import com.noob.apps.mahmoudsaad.models.HomeResponse
import com.noob.apps.mahmoudsaad.models.ProfileResponse
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