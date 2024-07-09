package com.alijan.tripay.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alijan.tripay.data.model.Contact
import com.alijan.tripay.databinding.ItemContactBinding

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private val itemList = ArrayList<Contact>()

    inner class ContactViewHolder(val itemContactBinding: ItemContactBinding) :
        RecyclerView.ViewHolder(itemContactBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemContactBinding.item = currentItem
    }

    fun updateList(newList: List<Contact>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

}