package com.gumigames.presentation.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.freeproject.happyprogrammers.base.BaseFragment
import com.gumigames.domain.model.user.UserInfoDto
import com.gumigames.presentation.BringGameInfoLoadingDialogFragment
import com.gumigames.presentation.MainViewModel
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentHomeBinding
import com.gumigames.presentation.util.addSirToName
import com.gumigames.presentation.util.isConnectingNetwork
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "차선호"
@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::bind,
    R.layout.fragment_home
) {
    private val mainViewModel: MainViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private val args: HomeFragmentArgs by navArgs()
    private val bringGameInfoLoadingDialogFragment = BringGameInfoLoadingDialogFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!args.isFromLogin) checkNetwork() //로그인해서 들어온 거 아니면 game 정보 조회
        else mainViewModel.getUserInfo() //로그인해서 들어온거면 user 정보 보자
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initCollect()
        collectErrorAndToken(mainViewModel)
    }

    private fun initProfileView(userInfo: UserInfoDto){
        binding.apply {
            Log.d(TAG, "initProfileView...")
            imageProfile.setImageBitmap(userInfo.imageBitmap)
            textviewNickname.text = addSirToName(userInfo.name)
        }
    }

    private fun checkPossibleLogin(){
        mainViewModel.checkRefreshToken()
    }

    private fun checkNetwork(){
        //TODO 네트워크 연결되어 있으면 아이템, 스킬, 몬스터 조회하기
        isConnectingNetwork(
            context = mActivity,
            onConnect = { //네트워크 연결되어 있음 -> game 정보 받아라
                homeViewModel.setIsConnected(true)
                checkPossibleLogin() //먼저 로그인 가능한 지 확인
            },
            onNotConnect = { //네트워크 연결 안되어 있음 -> 그냥 home으로 가라
                homeViewModel.setIsConnected(false)
                mainViewModel.getUserInfo()
                return@isConnectingNetwork
            }
        )
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

    private fun initCollect(){
        mainViewModel.apply{
            //사용자 정보 조회 상태 확인
            viewLifecycleOwner.lifecycleScope.launch {
                isBroughtGameInfo.collectLatest {
                    if(it && !args.isFromLogin) { //로그인해서 온 거 아니면 로딩창 닫자
                        bringGameInfoLoadingDialogFragment.dismiss()
                    }
                }
            }
            viewLifecycleOwner.lifecycleScope.launch {
                userInfo.collectLatest {
                    if(it!=null) {//로그인해서 온 거 아니면 로딩창 닫자
                        initProfileView(it)
                        if(!args.isFromLogin && homeViewModel.getIsConnected()) { //로그인에서 온 게 아니고 네트워크 연결되어 있으면
                            bringGameInfoLoadingDialogFragment.isCancelable = false
                            bringGameInfoLoadingDialogFragment.setStyle(
                                DialogFragment.STYLE_NORMAL,
                                R.style.FullScreenDialog
                            );
                            bringGameInfoLoadingDialogFragment.show(childFragmentManager, null)
                            bringGameInfo()
                        }
                    }
                }
            }
            viewLifecycleOwner.lifecycleScope.launch {
                isPossibleLogin.collectLatest {
                    if(it && !args.isFromLogin) getUserInfo() //로그인해서 온 게 아니면
                }
            }
        }
    }


}