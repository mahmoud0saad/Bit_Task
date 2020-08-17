package com.noob.apps.mahmoudsaad.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.noob.apps.mvvmcountries.R
import com.noob.apps.mvvmcountries.databinding.HomeListItemBinding
import com.noob.apps.mahmoudsaad.interfaces.ImageClickCallback
import com.noob.apps.mahmoudsaad.models.HomeResponse

class HomeListAdapter(var mList: List<HomeResponse.Data>? = listOf(), private val imageCallback: ImageClickCallback) :
    RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {

    fun setData(list: List<HomeResponse.Data>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding: HomeListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.home_list_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBinding.imageUrl = mList!!.get(position).image
        holder.itemBinding.ivPhoto.setOnClickListener {
            imageCallback.onImageClick(mList!!.get(position).image)
        }
    }

    class ViewHolder(var itemBinding: HomeListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
    }
}