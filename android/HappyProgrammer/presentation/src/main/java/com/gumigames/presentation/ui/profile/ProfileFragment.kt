package com.gumigames.presentation.ui.profile

import android.os.Bundle
import android.util.Log
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
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentProfileBinding
import com.gumigames.presentation.ui.common.item.ItemDetailDialogFragment
import com.gumigames.presentation.ui.common.item.ItemListApdapter
import com.gumigames.presentation.util.clickAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "차선호"
@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(
    FragmentProfileBinding::bind,
    R.layout.fragment_profile
) {

    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var itemListAdapter: MyItemListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initView()
        initListener()
        initCollect()
        collectErrorAndToken(profileViewModel)
    }

    private fun init(){
        profileViewModel.getUserInfo()
        profileViewModel.getAllMyItemsLocal { itemListAdapter.currentList }
        itemListAdapter = MyItemListAdapter()
    }

    private fun initView(){
        binding.apply {
            recyclerviewProfile.apply {
                adapter = itemListAdapter
                layoutManager = GridLayoutManager(mActivity, 3)
            }
        }
    }

    override fun initListener() {
        // 내 아이템 클릭 이벤트
        itemListAdapter.itemClickListner = object: MyItemListAdapter.ItemClickListener{
            override fun onClick(view: View, position: Int, item: ItemDto) {
                (view.parent as View).clickAnimation(viewLifecycleOwner)
                if(profileViewModel.getItemClickListenerEnabled()) {
                    profileViewModel.setItemClickListenerEnabled(false) // 클릭 이벤트 비활성화(다른 아이템 클릭 못하도록)
                    //해당 아이템 클릭 이벤트
                    profileViewModel.setSelectedMyItem(position, item)
                }
            }
        }
        binding.apply {
            //tab 이벤트
            tablayoutProfile.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab!!.position) {
                        //아이템 조회
                        0 -> {
                            recyclerviewProfile.adapter = itemListAdapter
                            profileViewModel.getAllMyItemsLocal { itemListAdapter.currentList }
                            profileViewModel.setCurrentTab("item")
                        }
                        //스킬 조회
                        1 -> {
                            profileViewModel.setCurrentTab("skill")
                        }
                        //몬스터 조회
                        2 -> {
                            profileViewModel.setCurrentTab("monster")
                        }
                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun initCollect(){
        profileViewModel.apply {
            //사용자 정보 관찰
            viewLifecycleOwner.lifecycleScope.launch {

            }
            //현재 내 아이템 리스트 관찰
            viewLifecycleOwner.lifecycleScope.launch {
                currentMyItemList.collectLatest {
                    Log.d(TAG, "collect -> $it")
                    itemListAdapter.submitList(it)
                }
            }
            //아이템 선택 관찰
            viewLifecycleOwner.lifecycleScope.launch {
                selectedMyItem.collectLatest {
                    if(it != null) {
                        val detailDialog = ItemDetailDialogFragment(
                            dogamViewModel = null,
                            bookmarkViewModel = null,
                            profileViewModel = profileViewModel
                        )
                        detailDialog.show(childFragmentManager, null)
                    }
                }
            }
            //바뀐 아이템 리스트 관찰
            viewLifecycleOwner.lifecycleScope.launch {
                newItemList.collectLatest {
                    itemListAdapter.submitList(it)
                }
            }
        }
    }
}