package com.freeproject.happyprogrammers.base

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.gumigames.presentation.MainActivity
import com.gumigames.presentation.R
import com.gumigames.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "차선호"
abstract class BaseFragment<B : ViewBinding>(
    private val bind: (View) -> B,
    @LayoutRes layoutResId: Int
) : Fragment(layoutResId) {
    private var _binding: B? = null
//    lateinit var mLoadingDialog: LoadingDialog

    protected val binding get() = _binding!!
    private lateinit var _mActivity: MainActivity
    protected val mActivity get() = _mActivity


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bind(super.onCreateView(inflater, container, savedInstanceState)!!)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        _mActivity = context as MainActivity
    }


    abstract fun initListener()


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    /**
     * 스낵바를 띄웁니다. 커스텀 하려면 type 분기를 추가하고 사용하세요.
     */
    fun showSnackbar(view: View, type: String, message: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackbar.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
        when(type){
            "success" ->{
                snackbar.setBackgroundTint(ContextCompat.getColor(requireActivity(), R.color.green_mild))
            }
            "fail" ->{
                snackbar.setBackgroundTint(ContextCompat.getColor(requireActivity(), R.color.red_mild))
            }
            "info" ->{
                snackbar.setBackgroundTint(ContextCompat.getColor(requireActivity(), R.color.gray_mild))
            }
        }
        // 텍스트 스타일 설정
    }

    fun collectErrorAndToken(
        viewModel: BaseViewModel
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            //네트워크 통신 시 Toast Message 출력
            viewModel.error.collectLatest {
                showSnackbar(binding.root, "fail", it.message.toString())
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            //REFRESH TOKEN 만료 시 로그 아웃
            viewModel.isExpiredRefreshToken.collectLatest {
                if(it) {
                    Log.d(TAG, "여기서 로그아웃 로직 수행하면 됨")
                    findNavController().navigate(R.id.loginFragment, null, navOptions{
                        popUpTo(R.id.homeFragment){
                            inclusive = true
                        }
                    })
                    showSnackbar(binding.root, "info", "장기간 로그인 하지 않아 다시 로그인 해주세요")
                    viewModel.initIsExpiredRefreshToken()
                }
            }
        }
    }



}