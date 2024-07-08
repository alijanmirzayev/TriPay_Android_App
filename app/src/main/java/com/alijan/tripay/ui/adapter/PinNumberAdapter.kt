package com.alijan.tripay.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alijan.tripay.databinding.ItemPinNumberBinding

class PinNumberAdapter : RecyclerView.Adapter<PinNumberAdapter.PinNumberViewHolder>() {

    private val itemList = ArrayList<String>()
    lateinit var onClickNumber: (number: String) -> Unit

    inner class PinNumberViewHolder(val itemPinNumberBinding: ItemPinNumberBinding) :
        RecyclerView.ViewHolder(itemPinNumberBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PinNumberViewHolder {
        val view = ItemPinNumberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PinNumberViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: PinNumberViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemPinNumberBinding.item = currentItem
        holder.itemPinNumberBinding.buttonItemPinNumber.setOnClickListener {
            onClickNumber(currentItem)
        }
    }

    fun updateList(newList: List<String>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

}