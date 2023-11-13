package com.gumigames.presentation.ui.bookmark

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.freeproject.happyprogrammers.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.domain.model.common.SkillDto
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentBookmarkBinding
import com.gumigames.presentation.ui.common.item.ItemDetailDialogFragment
import com.gumigames.presentation.ui.common.item.ItemListApdapter
import com.gumigames.presentation.ui.common.monster.MonsterDetailDialogFragment
import com.gumigames.presentation.ui.common.monster.MonsterListAdapter
import com.gumigames.presentation.ui.common.skill.SkillDetailDialogFragment
import com.gumigames.presentation.ui.common.skill.SkillListAdapter
import com.gumigames.presentation.util.clickAnimation
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
    private lateinit var skillListAdapter: SkillListAdapter
    private lateinit var monsterListAdapter: MonsterListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initView()
        initListener()
        initCollect()
        collectErrorAndToken(bookmarkViewModel)
    }

    private fun init(){
        bookmarkViewModel.getAllBookmarkItemsLocal()
        itemListAdapter = ItemListApdapter()
        skillListAdapter = SkillListAdapter()
        monsterListAdapter = MonsterListAdapter()
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
        //북마크 아이템 클릭 이벤트
        itemListAdapter.itemClickListner = object: ItemListApdapter.ItemClickListener{
            override fun onClick(view: View, position: Int, item: ItemDto) {
                (view.parent as View).clickAnimation(viewLifecycleOwner)
                if(bookmarkViewModel.getItemClickListenerEnabled()) {
                    bookmarkViewModel.setItemClickListenerEnabled(false) // 클릭 이벤트 비활성화(다른 아이템 클릭 못하도록)
                    //해당 아이템 클릭 이벤트
                    bookmarkViewModel.setSelectedBookmarkItem(item)
                }
            }
        }
        //북마크 스킬 클릭 이벤트
        skillListAdapter.itemClickListner = object: SkillListAdapter.ItemClickListener{
            override fun onClick(view: View, skill: SkillDto) {
                (view.parent as View).clickAnimation(viewLifecycleOwner)
                if(bookmarkViewModel.getItemClickListenerEnabled()) {
                    bookmarkViewModel.setItemClickListenerEnabled(false) // 클릭 이벤트 비활성화(다른 아이템 클릭 못하도록)
                    //해당 몬스터 클릭 이벤트
                    bookmarkViewModel.setSelectedBookmarkSkill(skill)
                }
            }

        }
        //북마크 몬스터 클릭 이벤트
        monsterListAdapter.itemClickListner = object: MonsterListAdapter.ItemClickListener{
            override fun onClick(view: View, monster: MonsterDto) {
                (view.parent as View).clickAnimation(viewLifecycleOwner)
                if(bookmarkViewModel.getItemClickListenerEnabled()) {
                    bookmarkViewModel.setItemClickListenerEnabled(false) // 클릭 이벤트 비활성화(다른 아이템 클릭 못하도록)
                    //해당 몬스터 클릭 이벤트
                    bookmarkViewModel.setSelectedBookmarkMonster(monster)
                }
            }

        }
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
                            binding.recyclerviewBookmark.adapter = skillListAdapter
                            bookmarkViewModel.getAllBookmarkSkillsLocal()
                            bookmarkViewModel.setCurrentTab("skill")
                        }
                        //몬스터 조회
                        2 -> {
                            binding.recyclerviewBookmark.adapter = monsterListAdapter
                            bookmarkViewModel.getAllBookmarkMonstersLocal()
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
            //아이템 선택 관찰
            viewLifecycleOwner.lifecycleScope.launch {
                selectedBookmarkItem.collectLatest {
                    if(it != null) {
                        val detailDialog = ItemDetailDialogFragment(
                            dogamViewModel = null,
                            bookmarkViewModel = bookmarkViewModel
                        )
                        detailDialog.show(childFragmentManager, null)
                    }
                }
            }
            //현재 북마크 스킬 리스트 관찰
            viewLifecycleOwner.lifecycleScope.launch {
                currentBookmarkSkillList.collectLatest {
                    skillListAdapter.submitList(it)
                }
            }
            //스킬 선택 관찰
            viewLifecycleOwner.lifecycleScope.launch {
                selectedBookmarkSkill.collectLatest {
                    if(it != null){
                        val detailDialog = SkillDetailDialogFragment(dogamViewModel = null, bookmarkViewModel = bookmarkViewModel)
                        detailDialog.show(childFragmentManager, null)
                    }
                }
            }
            //현재 북마크 몬스터 리스트 관찰
            viewLifecycleOwner.lifecycleScope.launch {
                currentBookmarkMonsterList.collectLatest {
                    monsterListAdapter.submitList(it)
                }
            }
            //몬스터 선택 관찰
            viewLifecycleOwner.lifecycleScope.launch {
                selectedBookmarkMonster.collectLatest {
                    if(it != null){
                        val detailDialog = MonsterDetailDialogFragment(dogamViewModel = null, bookmarkViewModel = bookmarkViewModel)
                        detailDialog.show(childFragmentManager, null)
                    }
                }
            }
        }

    }

}