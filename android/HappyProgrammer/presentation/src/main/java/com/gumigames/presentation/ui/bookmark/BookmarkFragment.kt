package com.gumigames.presentation.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.freeproject.happyprogrammers.base.BaseFragment
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentBookmarkBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(
    FragmentBookmarkBinding::bind,
    R.layout.fragment_bookmark
) {

    private val bookmarkViewModel: BookmarkViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookmarkViewModel.getAllBookmarkItemsLocal()
        initListener()
    }
    override fun initListener() {
    }
}