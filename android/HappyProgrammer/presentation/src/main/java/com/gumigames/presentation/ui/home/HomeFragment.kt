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
            //도감 화면으로 이동
            layoutDogam.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_dogamFragment)
            }
            //즐겨찾기 화면으로 이동
            layoutBookmark.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_bookmarkFragment)
            }
            //미션 화면으로 이동
            layoutMission.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_missionFragment)
            }
            //설정 화면으로 이동
            layoutSetting.setOnClickListener{
                findNavController().navigate(R.id.action_homeFragment_to_settingFragment)
            }
        }
    }
}