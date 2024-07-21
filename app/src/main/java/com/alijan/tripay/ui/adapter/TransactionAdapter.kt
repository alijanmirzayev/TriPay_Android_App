package com.alijan.tripay.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alijan.tripay.data.model.Transaction
import com.alijan.tripay.databinding.ItemTransactionBinding

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    private val itemList = ArrayList<Transaction>()

    inner class TransactionViewHolder(val itemTransactionBinding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(itemTransactionBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view =
            ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemTransactionBinding.item = currentItem
    }

    fun updateList(newList: List<Transaction>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

}