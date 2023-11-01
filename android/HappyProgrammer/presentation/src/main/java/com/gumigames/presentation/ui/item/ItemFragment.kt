package com.gumigames.presentation.ui.item

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.freeproject.happyprogrammers.base.BaseFragment
import com.gumigames.domain.model.item.ItemDto
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentItemBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ItemFragment : BaseFragment<FragmentItemBinding>(
    FragmentItemBinding::bind,
    R.layout.fragment_item
) {
    private val itemViewModel: ItemViewModel by viewModels()
    private lateinit var itemListAdapter: ItemListApdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initCollect()
        init()
    }

    private fun initView(){
        initItemRecyclerView()
    }

    private fun init(){
        itemViewModel.getTotalItemList()
    }

    private fun initItemRecyclerView(){
        itemListAdapter = ItemListApdapter()
        binding.recyclerviewItem.apply {
            adapter = itemListAdapter.apply {
                this.itemClickListner = object: ItemListApdapter.ItemClickListener{
                    override fun onClick(view: View, item: ItemDto) {
                        if(itemViewModel.getItemClickListenerEnabled()) {
                            itemViewModel.setItemClickListenerEnabled(false) // 클릭 이벤트 비활성화(다른 아이템 클릭 못하도록)
                            //해당 아이템 클릭 이벤트
                            val detailDialog = ItemDetailDialogFragment(itemViewModel)
                            detailDialog.show(childFragmentManager, null)
                        }
                    }
                }
            }
            layoutManager = GridLayoutManager(mActivity, 3)
        }
    }
    override fun initListener() {
        binding.apply {
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