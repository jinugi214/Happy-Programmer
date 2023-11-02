package com.gumigames.presentation.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide.init
import com.freeproject.happyprogrammers.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentBookmarkBinding
import com.gumigames.presentation.ui.dogam.item.ItemListApdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(
    FragmentBookmarkBinding::bind,
    R.layout.fragment_bookmark
) {

    private val bookmarkViewModel: BookmarkViewModel by viewModels()
    private lateinit var itemListAdapter: ItemListApdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookmarkViewModel.getAllBookmarkItemsLocal()
        init()
        initView()
        initListener()
        initCollect()
        collectErrorAndToken(bookmarkViewModel)
    }

    private fun init(){
        bookmarkViewModel.getAllBookmarkItemsLocal()
        itemListAdapter = ItemListApdapter()
    }

    private fun initView(){
        initBookmarkRecyclerView()
    }

    private fun initBookmarkRecyclerView(){
        binding.recyclerviewBookmark.apply {
            adapter = itemListAdapter
            layoutManager = GridLayoutManager(mActivity, 3)
        }
    }

    override fun initListener() {
        binding.apply {
            tablayoutBookmark.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab!!.position) {
                        //아이템 조회
                        0 -> {
                            binding.recyclerviewBookmark.adapter = itemListAdapter
                            bookmarkViewModel.getAllBookmarkItemsLocal()
                            bookmarkViewModel.setCurrentTab("item")
                        }
                        //스킬 조회
                        1 -> {
                            bookmarkViewModel.setCurrentTab("skill")
                        }
                        //몬스터 조회
                        2 -> {
                            bookmarkViewModel.setCurrentTab("monster")
                        }
                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun initCollect(){
        bookmarkViewModel.apply {
            //현재 북마크 아이템 리스트 관찰
            viewLifecycleOwner.lifecycleScope.launch {
                currentBookmarkItemList.collectLatest {
                    itemListAdapter.submitList(it)
                }
            }
        }

    }

}