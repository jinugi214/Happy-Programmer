package com.freeproject.happyprogrammers.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.freeproject.happyprogrammers.MainActivity
import com.freeproject.happyprogrammers.R
import com.google.android.material.snackbar.Snackbar

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

    fun showCustomToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

//    fun showLoadingDialog(context: Context) {
//        mLoadingDialog = LoadingDialog(context)
//        mLoadingDialog.show()
//    }
//
//    fun dismissLoadingDialog() {
//        if (mLoadingDialog.isShowing) {
//            mLoadingDialog.dismiss()
//        }
//    }

    /**
     * 스낵바를 띄웁니다. 커스텀 하려면 type 분기를 추가하고 사용하세요.
     */
    fun showSnackbar(view: View, type: String, message: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackbar.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
        snackbar.show()
    }
}