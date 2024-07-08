package com.alijan.tripay.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alijan.tripay.data.model.Onboarding
import com.alijan.tripay.databinding.ItemOnboardingBinding

class OnboardingAdapter : RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    private val itemList = ArrayList<Onboarding>()

    inner class OnboardingViewHolder(val itemOnboardingBinding: ItemOnboardingBinding) :
        RecyclerView.ViewHolder(itemOnboardingBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val view = ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnboardingViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemOnboardingBinding.item = currentItem
    }

    fun updateList(newList: List<Onboarding>) {
        itemList.clear()
        itemList.addAll(newList)
        notifyDataSetChanged()
    }

}