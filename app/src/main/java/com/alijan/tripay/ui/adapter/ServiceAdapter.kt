package com.alijan.tripay.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alijan.tripay.data.model.Service
import com.alijan.tripay.databinding.ItemServicesBinding

class ServiceAdapter : RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {

    private val itemList = ArrayList<Service>()
    lateinit var onClick: (title: String) -> Unit

    inner class ServiceViewHolder(val itemServicesBinding: ItemServicesBinding) :
        RecyclerView.ViewHolder(itemServicesBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = ItemServicesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ServiceViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemServicesBinding.item = currentItem
        holder.itemServicesBinding.clItemService.setOnClickListener {
            onClick(currentItem.title)
        }
    }

    fun updateList(newList: List<Service>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

}