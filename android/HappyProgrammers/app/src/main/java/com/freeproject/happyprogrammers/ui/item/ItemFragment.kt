package com.freeproject.happyprogrammers.ui.item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freeproject.happyprogrammers.R
import com.freeproject.happyprogrammers.base.BaseFragment
import com.freeproject.happyprogrammers.databinding.FragmentItemBinding
import com.google.android.material.tabs.TabLayout

class ItemFragment : BaseFragment<FragmentItemBinding>(
    FragmentItemBinding::bind,
    R.layout.fragment_item
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }
    override fun initListener() {
        binding.apply {
            tablayoutItem.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab!!.position){
                        //전체 아이템 조회
                        0 -> {}
                        //키보드 아이템 조회
                        1 -> {}
                        //마우스 아이템 조회
                        2 -> {}
                        //기타 아이템 조회
                        3 -> {}
                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }
}