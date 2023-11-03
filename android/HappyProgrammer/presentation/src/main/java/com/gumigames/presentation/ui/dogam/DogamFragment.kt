package com.gumigames.presentation.ui.dogam

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
import com.gumigames.presentation.databinding.FragmentDogamBinding
import com.gumigames.presentation.ui.common.item.ItemDetailDialogFragment
import com.gumigames.presentation.ui.common.item.ItemListApdapter
import com.gumigames.presentation.ui.common.monster.MonsterDetailDialogFragment
import com.gumigames.presentation.ui.common.monster.MonsterListAdapter
import com.gumigames.presentation.ui.common.skill.SkillDetailDialogFragment
import com.gumigames.presentation.ui.common.skill.SkillListAdapter
import com.gumigames.presentation.util.clickAnimation
import com.gumigames.presentation.util.clickEnterListener
import com.gumigames.presentation.util.hideKeyboard
import com.gumigames.presentation.util.setTextListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DogamFragment : BaseFragment<FragmentDogamBinding>(
    FragmentDogamBinding::bind,
    R.layout.fragment_dogam
) {
    private val dogamViewModel: DogamViewModel by viewModels()
    private lateinit var itemListAdapter: ItemListApdapter
    private lateinit var monsterListAdapter: MonsterListAdapter
    private lateinit var skillListAdapter: SkillListAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initView()
        initListener()
        initCollect()
        collectErrorAndToken(dogamViewModel)
    }

    private fun initView(){
        initDogamRecyclerView()
    }

    private fun init(){
        dogamViewModel.getAllItems()
        itemListAdapter = ItemListApdapter()
        skillListAdapter = SkillListAdapter()
        monsterListAdapter = MonsterListAdapter()
    }

    private fun initDogamRecyclerView(){
        binding.recyclerviewDogam.apply {
            adapter = itemListAdapter
            layoutManager = GridLayoutManager(mActivity, 3)
        }
    }
    override fun initListener() {
        //아이템 클릭 이벤트
        itemListAdapter.itemClickListner = object: ItemListApdapter.ItemClickListener{
            override fun onClick(view: View, item: ItemDto) {
                (view.parent as View).clickAnimation(viewLifecycleOwner)
                if(dogamViewModel.getItemClickListenerEnabled()) {
                    dogamViewModel.setItemClickListenerEnabled(false) // 클릭 이벤트 비활성화(다른 아이템 클릭 못하도록)
                    //해당 아이템 클릭 이벤트
                    dogamViewModel.setSelectedItem(item)
                }
            }
        }
        //스킬 클릭 이벤트
        skillListAdapter.itemClickListner = object: SkillListAdapter.ItemClickListener{
            override fun onClick(view: View, skill: SkillDto) {
                (view.parent as View).clickAnimation(viewLifecycleOwner)
                if(dogamViewModel.getItemClickListenerEnabled()) {
                    dogamViewModel.setItemClickListenerEnabled(false) // 클릭 이벤트 비활성화(다른 아이템 클릭 못하도록)
                    //해당 몬스터 클릭 이벤트
                    dogamViewModel.setSelectedSkill(skill)
                }
            }

        }
        //몬스터 클릭 이벤트
        monsterListAdapter.itemClickListner = object: MonsterListAdapter.ItemClickListener{
            override fun onClick(view: View, monster: MonsterDto) {
                (view.parent as View).clickAnimation(viewLifecycleOwner)
                if(dogamViewModel.getItemClickListenerEnabled()) {
                    dogamViewModel.setItemClickListenerEnabled(false) // 클릭 이벤트 비활성화(다른 아이템 클릭 못하도록)
                    //해당 몬스터 클릭 이벤트
                    dogamViewModel.setSelectedMonster(monster)
                }
            }

        }
        binding.apply {
            tablayoutDogam.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab!!.position){
                        //아이템 조회
                        0 -> {
                            binding.recyclerviewDogam.adapter = itemListAdapter
                            dogamViewModel.getAllItems()
                            dogamViewModel.setCurrentTab("item")
                            edittextSearch.text.clear()
                        }
                        //스킬 조회
                        1 -> {
                            binding.recyclerviewDogam.adapter = skillListAdapter
                            dogamViewModel.getAllSkills()
                            dogamViewModel.setCurrentTab("skill")
                            edittextSearch.text.clear()
                        }
                        //몬스터 조회
                        2 -> {
                            binding.recyclerviewDogam.adapter = monsterListAdapter
                            dogamViewModel.getAllMonsters()
                            dogamViewModel.setCurrentTab("monster")
                            edittextSearch.text.clear()
                        }
                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })

            edittextSearch.setTextListener {
                //검색 키워드 갱신
                dogamViewModel.setSearchKeyword(it)
            }
            edittextSearch.clickEnterListener {
                //검색 이벤트
                dogamViewModel.searchKeyword()
                hideKeyboard(mActivity)
            }
            imageSearch.setOnClickListener {
                //검색 이벤트
                dogamViewModel.searchKeyword()
                hideKeyboard(mActivity)
            }
        }
    }

    private fun initCollect(){
        dogamViewModel.apply {

            //현재 아이템 리스트 관찰
            viewLifecycleOwner.lifecycleScope.launch {
                currentItemList.collectLatest {
                    itemListAdapter.submitList(it)
                }
            }
            //아이템 선택 관찰
            viewLifecycleOwner.lifecycleScope.launch {
                selectedItem.collectLatest {
                    if(it != null) {
                        addBookmarkItemLocal(it) //이거 나중에 itemDetailDialogFragment로 빼라
                        val detailDialog = ItemDetailDialogFragment(dogamViewModel = dogamViewModel, bookmarkViewModel = null)
                        detailDialog.show(childFragmentManager, null)
                    }
                }
            }
            //현재 스킬 리스트 관찰
            viewLifecycleOwner.lifecycleScope.launch {
                currentSkillList.collectLatest {
                    skillListAdapter.submitList(it)
                }
            }
            //스킬 선택 관찰
            viewLifecycleOwner.lifecycleScope.launch {
                selectedSkill.collectLatest {
                    if(it != null) {
                        addBookmarkSkillLocal(it) //이거 나중에 skillDetailDialogFragment로 빼라
                        val detailDialog = SkillDetailDialogFragment(dogamViewModel = dogamViewModel, bookmarkViewModel = null)
                        detailDialog.show(childFragmentManager, null)
                    }
                }
            }
            //현재 몬스터 리스트 관찰
            viewLifecycleOwner.lifecycleScope.launch {
                currentMonsterList.collectLatest {
                    monsterListAdapter.submitList(it)
                }
            }
            //몬스터 선택 관찰
            viewLifecycleOwner.lifecycleScope.launch {
                selectedMonster.collectLatest {
                    if(it != null){
                        addBookmarkMonsterLocal(it) //이거 나중에 skillDetailDialogFragment로 빼라
                        val detailDialog = MonsterDetailDialogFragment(dogamViewModel = dogamViewModel, bookmarkViewModel = null)
                        detailDialog.show(childFragmentManager, null)
                    }
                }
            }
        }
    }
}