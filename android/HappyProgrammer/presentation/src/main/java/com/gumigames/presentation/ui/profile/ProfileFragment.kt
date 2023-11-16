package com.gumigames.presentation.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide.init
import com.freeproject.happyprogrammers.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.model.user.UserInfoDto
import com.gumigames.presentation.MainViewModel
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentProfileBinding
import com.gumigames.presentation.ui.common.item.ItemDetailDialogFragment
import com.gumigames.presentation.ui.common.item.ItemListApdapter
import com.gumigames.presentation.ui.common.monster.MonsterListAdapter
import com.gumigames.presentation.ui.common.skill.SkillListAdapter
import com.gumigames.presentation.util.clickAnimation
import com.gumigames.presentation.util.levelAndExpFormat
import com.gumigames.presentation.util.nameAndGenderFormat
import com.gumigames.presentation.util.numberFormat
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "차선호"
@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(
    FragmentProfileBinding::bind,
    R.layout.fragment_profile
) {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val profileViewModel: ProfileViewModel by viewModels()
    private lateinit var itemListAdapter: MyItemListAdapter
    private lateinit var skillListAdapter: SkillListAdapter
    private lateinit var monsterListAdapter: MonsterListAdapter
    private lateinit var userInfo: UserInfoDto

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
        userInfo = mainViewModel.userInfo.value!!
        skillListAdapter = SkillListAdapter()
        monsterListAdapter = MonsterListAdapter()
    }

    private fun initView(){
        binding.apply {
            imageProfile.setImageBitmap(userInfo.imageBitmap)
            textviewNameGender.text = nameAndGenderFormat(userInfo.name, userInfo.gender)
            textviewLevelExp.text = levelAndExpFormat(userInfo.level, userInfo.exp)
            textviewPoint.text = numberFormat(userInfo.point)
            textviewStory.text = numberFormat(userInfo.storyProgress)
            textviewSavePoint.text = userInfo.savepoint
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
                            layoutReadyInfo.visibility = View.GONE
                            recyclerviewProfile.visibility = View.VISIBLE
                        }
                        //스킬 조회
                        1 -> {
                            recyclerviewProfile.adapter = skillListAdapter
                            profileViewModel.getAllMySkillsLocal()
                            profileViewModel.setCurrentTab("skill")
                            layoutReadyInfo.visibility = View.VISIBLE
                            recyclerviewProfile.visibility = View.GONE
                        }
                        //몬스터 조회
                        2 -> {
                            recyclerviewProfile.adapter = monsterListAdapter
                            profileViewModel.getAllMyMonstersLocal()
                            profileViewModel.setCurrentTab("monster")
                            layoutReadyInfo.visibility = View.VISIBLE
                            recyclerviewProfile.visibility = View.GONE
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