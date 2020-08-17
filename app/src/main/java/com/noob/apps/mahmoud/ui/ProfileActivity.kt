package com.noob.apps.mahmoud.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.noob.apps.mvvmcountries.R
import com.noob.apps.mahmoud.adapters.HomeListAdapter
import com.noob.apps.mvvmcountries.databinding.ActivityProfileBinding
import com.noob.apps.mahmoud.interfaces.ImageClickCallback
import com.noob.apps.mahmoud.viewmodels.ProfileViewModel

class ProfileActivity : AppCompatActivity() ,
    ImageClickCallback {
    private lateinit var mAdapter: HomeListAdapter
    private lateinit var mViewModel: ProfileViewModel
    private lateinit var mActivityBinding: ActivityProfileBinding
    companion object val IMAGE_URL_KEY: String="imageUrlKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_profile)

        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        mActivityBinding.lifecycleOwner = this

        initializeRecyclerView()
        initializeObservers()

        mViewModel.fetchProfileFromServer(this, false).observe(this, Observer { kt ->
            if (kt==null)return@Observer
            mActivityBinding.profile=kt
        })



    }

    private fun initializeRecyclerView() {
        mActivityBinding.recyclerView.setHasFixedSize(true)
        mActivityBinding.recyclerView.layoutManager = GridLayoutManager(this,3)
        mAdapter = HomeListAdapter(
            mViewModel.getHomeList().value,
            this
        )
        mActivityBinding.recyclerView.adapter = mAdapter
    }

    private fun initializeObservers() {
        mViewModel.fetchHomeFromServer(this, false).observe(this, Observer { kt ->
            if (kt!=null)
            mAdapter.setData(kt)
        })

        mViewModel.mShowProgressBar.observe(this, Observer { bt ->
            if (bt) {
                mActivityBinding.progressBar.visibility = View.VISIBLE
            } else {
                mActivityBinding.progressBar.visibility = View.GONE
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onImageClick(image: String) {
        var intent =Intent(this, ImageActivity::class.java)
        intent.putExtra(IMAGE_URL_KEY,image)
        startActivity(intent)

    }
}