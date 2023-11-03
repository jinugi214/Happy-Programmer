package com.gumigames.presentation.ui.mission

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freeproject.happyprogrammers.base.BaseFragment
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentMissionBinding

class MissionFragment: BaseFragment<FragmentMissionBinding>(
    FragmentMissionBinding::bind,
    R.layout.fragment_mission
){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }
    override fun initListener() {
    }

}