package com.freeproject.happyprogrammers.ui.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.freeproject.happyprogrammers.R
import com.freeproject.happyprogrammers.base.BaseFragment
import com.freeproject.happyprogrammers.data.model.ItemDto
import com.freeproject.happyprogrammers.databinding.FragmentItemBinding
import com.freeproject.happyprogrammers.util.GridSpacingItemDecoration
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "차선호"
class ItemFragment : BaseFragment<FragmentItemBinding>(
    FragmentItemBinding::bind,
    R.layout.fragment_item
) {
    private val itemViewModel: ItemViewModel by viewModels()
    private lateinit var itemListAdapter: ItemListApdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initListener()
        initCollect()
        init()
    }

    private fun init(){
        itemViewModel.getTotalItemList()
    }

    private fun initRecyclerView(){
        itemListAdapter = ItemListApdapter()
        binding.recyclerviewItem.apply {
            adapter = itemListAdapter.apply {
                this.itemClickListner = object: ItemListApdapter.ItemClickListener{
                    override fun onClick(view: View, item: ItemDto) {
                        //해당 아이템 클릭 이벤트
                    }
                }
            }
//            addItemDecoration(GridSpacingItemDecoration(3, mActivity.resources.getDimensionPixelSize(com.intuit.sdp.R.dimen._10sdp)))
            layoutManager = GridLayoutManager(mActivity, 3)
        }
    }
    override fun initListener() {
        binding.apply {
            tablayoutItem.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab!!.position){
                        //전체 아이템 조회
                        0 -> {itemViewModel.getTotalItemList()}
                        //키보드 아이템 조회
                        1 -> {itemViewModel.getKeyboardItemList()}
                        //마우스 아이템 조회
                        2 -> {itemViewModel.getMouseItemList()}
                        //기타 아이템 조회
                        3 -> {itemViewModel.getEtcItemList()}
                    }
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun initCollect(){
        itemViewModel.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                currentItemList.collectLatest {
                    itemListAdapter.submitList(it)
                }
            }
        }
    }
}