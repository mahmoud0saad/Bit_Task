package com.noob.apps.mahmoudsaad.ui

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.noob.apps.mvvmcountries.R
import com.noob.apps.mvvmcountries.databinding.ActivityImageBinding
import com.squareup.picasso.Picasso

class ImageActivity : AppCompatActivity()  {
    private lateinit var mActivityBinding: ActivityImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        mActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_image)


        mActivityBinding.lifecycleOwner = this


        val values: String? = intent.getStringExtra("imageUrlKey")
        if(values == null){
            finish()
            return;
        }else{
            Picasso.get()
                .load(values)
                .placeholder(com.noob.apps.mvvmcountries.R.mipmap.ic_launcher)
                .error(com.noob.apps.mvvmcountries.R.mipmap.ic_launcher)
                .into(mActivityBinding.image)
        }
        mActivityBinding.ivBack.setOnClickListener {
            finish()
        }



    }



    override fun onDestroy() {
        super.onDestroy()
    }


}