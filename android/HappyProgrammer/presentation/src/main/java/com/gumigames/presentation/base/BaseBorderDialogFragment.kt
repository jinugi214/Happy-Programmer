package com.freeproject.happyprogrammers.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
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

abstract class BaseBorderDialogFragment<B : ViewBinding>(
    private val bind: (View) -> B,
    @LayoutRes layoutResId: Int
): DialogFragment(layoutResId) {
    private var _binding: B? = null
    protected val binding get() = _binding!!
    protected lateinit var mActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bind(super.onCreateView(inflater, container, savedInstanceState)!!)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.frame_border_white30)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as MainActivity
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        return dialog
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

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
                snackbar.setBackgroundTint(ContextCompat.getColor(requireActivity(), R.color.main_pink))
            }
        }
        snackbar.show()
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