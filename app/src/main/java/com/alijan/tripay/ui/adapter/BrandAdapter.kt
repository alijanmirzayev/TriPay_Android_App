package com.alijan.tripay.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alijan.tripay.data.model.Brand
import com.alijan.tripay.databinding.ItemBrandBinding

class BrandAdapter : RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {

    private val itemList = ArrayList<Brand>()

    inner class BrandViewHolder(val itemBrandBinding: ItemBrandBinding) :
        RecyclerView.ViewHolder(itemBrandBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val view = ItemBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrandViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemBrandBinding.item = currentItem
    }

    fun updateList(newList: List<Brand>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

}