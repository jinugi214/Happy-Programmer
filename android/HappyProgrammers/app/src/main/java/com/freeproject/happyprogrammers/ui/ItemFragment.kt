package com.freeproject.happyprogrammers.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freeproject.happyprogrammers.R
import com.freeproject.happyprogrammers.base.BaseFragment
import com.freeproject.happyprogrammers.databinding.FragmentItemBinding

class ItemFragment : BaseFragment<FragmentItemBinding>(
    FragmentItemBinding::bind,
    R.layout.fragment_item
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }
    override fun initListener() {
    }
}