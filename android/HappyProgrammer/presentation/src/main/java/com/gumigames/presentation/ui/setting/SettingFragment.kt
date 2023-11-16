package com.gumigames.presentation.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.freeproject.happyprogrammers.base.BaseFragment
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentSettingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(
    FragmentSettingBinding::bind,
    R.layout.fragment_setting
) {

    private val settingViewModel: SettingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initCollect()
    }

    override fun initListener() {
        binding.apply {
            layoutLogout.setOnClickListener {
                lifecycleScope.launch {
                    settingViewModel.logout()
                }
            }
        }
    }

    private fun initCollect(){
        settingViewModel.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                isLogouted.collectLatest {
                    if(it){
                        findNavController().navigate(R.id.loginFragment, null, navOptions{
                            popUpTo(R.id.homeFragment){
                                inclusive = true
                            }
                        })
                    }
                }
            }
        }
    }
}