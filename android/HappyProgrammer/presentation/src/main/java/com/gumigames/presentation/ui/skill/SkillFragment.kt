package com.gumigames.presentation.ui.skill

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.freeproject.happyprogrammers.base.BaseFragment
import com.gumigames.domain.model.item.ItemDto
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentSkillBinding
import com.gumigames.presentation.ui.common.SearchListAdapter
import com.gumigames.presentation.ui.item.ItemDetailDialogFragment
import com.gumigames.presentation.ui.item.ItemListApdapter
import com.gumigames.presentation.util.clickEnterListener
import com.gumigames.presentation.util.setFocusListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "차선호"
@AndroidEntryPoint
class SkillFragment : BaseFragment<FragmentSkillBinding>(
    FragmentSkillBinding::bind,
    R.layout.fragment_skill
) {

    private val skillViewModel: SkillViewModel by viewModels()
    private lateinit var searchListAdapter: SearchListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initCollect()
    }

    private fun initView(){
        binding.apply {
        }
    }


    override fun initListener() {
        binding.apply {
            edittextSearch.clickEnterListener {
                //여기에 검색 실행 로직
            }
            imageSearch.setOnClickListener {
                //여기에 검색 실행 로직
            }
        }
    }

    private fun initCollect(){
        skillViewModel.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                searchTextList.collectLatest {
                    searchListAdapter.submitList(it)
                }
            }
        }
    }
}