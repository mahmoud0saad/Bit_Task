package com.noob.apps.mahmoud.models

import android.app.Activity
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object BindingAdapters {
    @BindingAdapter("app:imageThumb")
    @JvmStatic
    fun loadImage(imageView: ImageView?, imageUrl: String?) {
        Log.i("mano", "loadImage: "+imageUrl)
        Picasso.get()
            .load(imageUrl)
            .placeholder(com.noob.apps.mvvmcountries.R.mipmap.ic_launcher)
            .error(com.noob.apps.mvvmcountries.R.mipmap.ic_launcher)
            .into(imageView);

    }
    @BindingAdapter("app:setInt")
    @JvmStatic
    fun toStrings(textview:TextView ,num:Int) {
       textview.setText(num.toString())

    }
}