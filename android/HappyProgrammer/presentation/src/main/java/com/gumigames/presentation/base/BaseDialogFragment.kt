package com.freeproject.happyprogrammers.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.gumigames.presentation.MainActivity
import com.gumigames.presentation.R

abstract class BaseDialogFragment<B : ViewBinding>(
    private val bind: (View) -> B,
    @LayoutRes layoutResId: Int
): DialogFragment(layoutResId) {
    private var _binding: B? = null
    protected val binding get() = _binding!!
    private lateinit var _mActivity: MainActivity
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
        _mActivity = context as MainActivity
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
}