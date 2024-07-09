package com.alijan.tripay.ui.sendcash.receiver

import com.alijan.tripay.data.model.Contact
import com.alijan.tripay.databinding.FragmentReceiverBinding
import com.alijan.tripay.ui.BaseFragment
import com.alijan.tripay.ui.adapter.ContactAdapter

class ReceiverFragment : BaseFragment<FragmentReceiverBinding>() {
    private val contactAdapter = ContactAdapter()
    private val contactList = arrayListOf(
        Contact("Alijan Mirzayev","+994 70 241 5052","AM"),
        Contact("Alijan Mirzayev","+994 70 241 5052","AM"),
        Contact("Alijan Mirzayev","+994 70 241 5052","AM"),
        Contact("Alijan Mirzayev","+994 70 241 5052","AM"),
        Contact("Alijan Mirzayev","+994 70 241 5052","AM"),
        Contact("Alijan Mirzayev","+994 70 241 5052","AM"),
        Contact("Alijan Mirzayev","+994 70 241 5052","AM")
    )
    override fun layoutInflater(): FragmentReceiverBinding = FragmentReceiverBinding.inflate(layoutInflater)

    override fun setupUI() {
        setAdapter()
    }

    private fun setAdapter(){
        binding.rvReceiverContact.adapter = contactAdapter
        contactAdapter.updateList(contactList)
    }

}