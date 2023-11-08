package com.gumigames.presentation.ui.login

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
import com.freeproject.happyprogrammers.base.BaseFragment
import com.gumigames.presentation.MainViewModel
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentLoginBinding
import com.gumigames.presentation.util.clickEnterListener
import com.gumigames.presentation.util.setTextListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.log

private const val TAG = "차선호"
@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::bind,
    R.layout.fragment_login
) {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private val bringUserInfoLoadingDialogFragment = BringUserInfoLoadingDialogFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initCollect()
        collectErrorAndToken(loginViewModel)
    }

    override fun initListener() {
        binding.apply {
            //ID 입력 받는 LISTENER
            edittextId.apply {
                setTextListener { it -> loginViewModel.setId(it) }
                clickEnterListener { edittextPw.requestFocus() }
            }
            //PW 입력 받는 LISTENER
            edittextPw.setTextListener { it -> loginViewModel.setPw(it) }
            //로그인 클릭
            buttonLogin.setOnClickListener {
                loginViewModel.login()
            }
        }
    }

    private fun initCollect(){
        loginViewModel.apply {
            //로그인 결과 확인
            viewLifecycleOwner.lifecycleScope.launch {
                loginResult.collectLatest {
                    if (it) {//로그인 성공 -> 게임 정보 조회하자 & 이때 로딩 다이얼로그 띄우자
                        // bringUserInfoLoadingDialogFragment가 떠있는 동안 loginFragment의 터치 이벤트를 무시
                        bringUserInfoLoadingDialogFragment.isCancelable = false
                        bringUserInfoLoadingDialogFragment.setStyle(
                            DialogFragment.STYLE_NORMAL,
                            R.style.FullScreenDialog
                        );
                        bringUserInfoLoadingDialogFragment.show(childFragmentManager, null)
                        mainViewModel.bringGameInfo()
                    } else {//로그인 실패 -> 아이디, 비밀번호 확인

                    }
                }
            }
        }
        mainViewModel.apply{
            //사용자 정보 조회 상태 확인
            viewLifecycleOwner.lifecycleScope.launch {
                isBroughtGameInfo.collectLatest {
                    if(it) {
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        bringUserInfoLoadingDialogFragment.dismiss()
                    }

                }
            }
        }
    }

}