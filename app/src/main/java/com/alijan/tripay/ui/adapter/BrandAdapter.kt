package com.alijan.tripay.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alijan.tripay.data.model.Brand
import com.alijan.tripay.databinding.ItemBrandBinding

class BrandAdapter : RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {

    private val itemList = ArrayList<Brand>()

        lateinit var onClick: (brandName: String) -> Unit

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

        holder.itemBrandBinding.cardViewItemBrand.setOnClickListener {
            Log.d("BrandAdapter", "Item clicked: ${currentItem.brandName}")
            onClick(currentItem.brandName)
        }


        holder.itemBrandBinding.cardViewItemBrand.setOnClickListener {
            onClick(currentItem.brandName)
            itemList.forEachIndexed { index, it ->
                if(it.brandName == currentItem.brandName){
                    it.isSelected = true
                    notifyItemChanged(position)
                } else if (it.isSelected == true){
                    it.isSelected = false
                    notifyItemChanged(index)
                }
            }
        }

    }

    fun updateList(newList: List<Brand>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

}