package com.anshad.whetherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anshad.whetherapp.R
import com.anshad.whetherapp.databinding.WetherListitemBinding
import com.anshad.whetherapp.model.ListData
import com.anshad.whetherapp.utils.ItemViewPosClickCallback
import com.qaptive.advancedrecyclerview.AdvancedRecyclerViewAdapter

class WhetherAdapter (val callBack: ItemViewPosClickCallback<ListData>) :
    AdvancedRecyclerViewAdapter<ListData, WhetherAdapter.ViewHolder>(),
    ItemViewPosClickCallback<ListData> {


    class ViewHolder(val binder: WetherListitemBinding) :
        RecyclerView.ViewHolder(binder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binder: WetherListitemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.wether_listitem,
                parent,
                false
            )
        binder.callback = callBack
        return WhetherAdapter.ViewHolder(binder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binder.weather = data?.get(position)
        holder.binder.container.setOnClickListener {
            data?.get(position)?.let { it1 ->
                callBack.onViewClick(
                    holder.binder.container,
                    it1, position,false
                )
            }
        }
    }

    override fun onViewClick(view: View?, item: ListData, position: Int, status: Boolean) {

    }


}