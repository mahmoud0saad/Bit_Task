package com.noob.apps.mahmoud.repositories

import androidx.lifecycle.MutableLiveData
import com.noob.apps.mahmoud.interfaces.NetworkResponseCallback
import com.noob.apps.mahmoud.models.Country
import com.noob.apps.mahmoud.models.HomeResponse
import com.noob.apps.mahmoud.models.ProfileResponse
import com.noob.apps.mahmoud.network.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountriesRepository private constructor() {
    private lateinit var mCallback: NetworkResponseCallback
    private var mCountryList: MutableLiveData<List<Country>> =
        MutableLiveData<List<Country>>().apply { value = emptyList() }
    private var mHomeList: MutableLiveData<List<HomeResponse.Data>> =
        MutableLiveData<List<HomeResponse.Data>>().apply { value = emptyList() }
    private var mProfile: MutableLiveData<ProfileResponse> =
        MutableLiveData<ProfileResponse>().apply { value = null }
    private var mProfileValue: MutableLiveData<List<Country>> =
        MutableLiveData<List<Country>>().apply { value = emptyList() }

    companion object {
        private var mInstance: CountriesRepository? = null
        fun getInstance(): CountriesRepository {
            if (mInstance == null) {
                synchronized(this) {
                    mInstance =
                        CountriesRepository()
                }
            }
            return mInstance!!
        }
    }


    private lateinit var mHomeCall: Call<HomeResponse>
    private lateinit var mProfileCall: Call<ProfileResponse>

    fun getHome(
        callback: NetworkResponseCallback,
        forceFetch: Boolean
    ): MutableLiveData<List<HomeResponse.Data>> {
        mCallback = callback
        if (!mHomeList.value!!.isEmpty() && !forceFetch) {
            mCallback.onNetworkSuccess()
            return mHomeList
        }
        mHomeCall = RestClient.getInstance().getApiService().getHome()
        mHomeCall.enqueue(object : Callback<HomeResponse> {

            override fun onResponse(call: Call<HomeResponse>, response: Response<HomeResponse>) {
                mHomeList.value = response.body()?.data
                mCallback.onNetworkSuccess()
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                mHomeList.value = emptyList()
                mCallback.onNetworkFailure(t)
            }

        })
        return mHomeList
    }

    fun getProfile(
        callback: NetworkResponseCallback,
        forceFetch: Boolean
    ): MutableLiveData<ProfileResponse> {
        mCallback = callback
        if (forceFetch) {
            mCallback.onNetworkSuccess()
            return mProfile
        }
        mProfileCall = RestClient.getInstance().getApiService().getProfile()
        mProfileCall.enqueue(object : Callback<ProfileResponse> {

            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                mProfile.value = response.body()
                mCallback.onNetworkSuccess()
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                mCountryList.value = emptyList()
                mCallback.onNetworkFailure(t)
            }

        })
        return mProfile
    }

}