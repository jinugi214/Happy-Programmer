package com.gumigames.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.freeproject.happyprogrammers.base.BaseFragment
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::bind,
    R.layout.fragment_home
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    override fun initListener() {
        binding.apply {
            //프로필 상세 화면으로 이동
            layoutProfile.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
            }
            //아이템 화면으로 이동
            layoutItem.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_itemFragment)
            }
        }
    }
}