package com.noob.apps.mahmoud.viewmodels

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.noob.apps.mahmoud.interfaces.NetworkResponseCallback
import com.noob.apps.mahmoud.models.Country
import com.noob.apps.mahmoud.models.HomeResponse
import com.noob.apps.mahmoud.models.ProfileResponse
import com.noob.apps.mahmoud.repositories.CountriesRepository
import com.noob.apps.mahmoud.utils.NetworkHelper
import java.util.*

class ProfileViewModel : ViewModel() {
    private var mList: MutableLiveData<List<HomeResponse.Data>> =
        MutableLiveData<List<HomeResponse.Data>>().apply { value = emptyList() }
    private var mProfile: MutableLiveData<ProfileResponse> =
        MutableLiveData<ProfileResponse>().apply { value =
            ProfileResponse(
                data = ProfileResponse.Data(
                    "",
                    counts = ProfileResponse.Data.Counts(
                        2,
                        2,
                        2
                    ),
                    full_name = "",
                    id = 1,
                    location = "",
                    profile_picture = ""
                ), status = false
            )
        }
    var mShowProgressBar: MutableLiveData<Boolean> = MutableLiveData()
    private var mShowNetworkError: MutableLiveData<Boolean> = MutableLiveData()
    private var mShowApiError: MutableLiveData<Boolean> = MutableLiveData()
    private var mRepository = CountriesRepository.getInstance()

    fun getHomeList() = mList

    fun fetchHomeFromServer(context: Context, forceFetch : Boolean): MutableLiveData<List<HomeResponse.Data>> {
        if (NetworkHelper.isOnline(context)) {
            mShowProgressBar.value = true
            mList = mRepository.getHome(object :
                NetworkResponseCallback {
                override fun onNetworkFailure(th: Throwable) {
                    mShowApiError.value = true
                }

                override fun onNetworkSuccess() {
                    mShowProgressBar.value = false
                }
            }, forceFetch)
        } else {
            mShowNetworkError.value = true
        }
        return mList
    }

    fun fetchProfileFromServer(context: Context, forceFetch : Boolean): MutableLiveData<ProfileResponse> {
        if (NetworkHelper.isOnline(context)) {
            mShowProgressBar.value = true
            mProfile = mRepository.getProfile(object :
                NetworkResponseCallback {
                override fun onNetworkFailure(th: Throwable) {
                    mShowApiError.value = true
                }

                override fun onNetworkSuccess() {
                    mShowProgressBar.value = false
                }
            }, forceFetch)
        } else {
            mShowNetworkError.value = true
        }
        return mProfile
    }
}