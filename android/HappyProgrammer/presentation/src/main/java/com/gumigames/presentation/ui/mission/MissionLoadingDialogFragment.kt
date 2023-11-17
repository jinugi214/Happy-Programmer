package com.gumigames.presentation.ui.mission

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freeproject.happyprogrammers.base.BaseBorderDialogFragment
import com.gumigames.presentation.R
import com.gumigames.presentation.base.BaseBorderAlphaDialogFragment
import com.gumigames.presentation.base.BaseDialogFragment
import com.gumigames.presentation.databinding.FragmentMissionLoadingDialogBinding


class MissionLoadingDialogFragment: BaseBorderAlphaDialogFragment<FragmentMissionLoadingDialogBinding>(
    FragmentMissionLoadingDialogBinding::bind,
    R.layout.fragment_mission_loading_dialog
){
}