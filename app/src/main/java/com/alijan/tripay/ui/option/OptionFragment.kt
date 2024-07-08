package com.alijan.tripay.ui.option

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.alijan.tripay.R
import com.alijan.tripay.databinding.FragmentOptionBinding
import com.alijan.tripay.ui.BaseFragment

class OptionFragment : BaseFragment<FragmentOptionBinding>() {
    override fun layoutInflater(): FragmentOptionBinding = FragmentOptionBinding.inflate(layoutInflater)

    override fun setupUI() {
    }

}