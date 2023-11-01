package com.gumigames.presentation.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.freeproject.happyprogrammers.base.BaseFragment
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentLoginBinding
import com.gumigames.presentation.util.clickEnterListener
import com.gumigames.presentation.util.setTextListener
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "차선호"
@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::bind,
    R.layout.fragment_login
) {

    private val loginViewModel: LoginViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
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

            buttonLogin.setOnClickListener {
                Log.d(TAG, "id : ${loginViewModel.getId()} // pw : ${loginViewModel.getPw()}")
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
    }

}