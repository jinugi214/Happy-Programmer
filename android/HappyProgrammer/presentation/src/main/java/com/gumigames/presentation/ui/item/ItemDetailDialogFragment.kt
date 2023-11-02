package com.gumigames.presentation.ui.item

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.freeproject.happyprogrammers.base.BaseDialogFragment
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentItemDetailDialogBinding
import kotlinx.coroutines.flow.single

class ItemDetailDialogFragment(
    private val itemViewModel: ItemViewModel
) : BaseDialogFragment<FragmentItemDetailDialogBinding>(
    FragmentItemDetailDialogBinding::bind,
    R.layout.fragment_item_detail_dialog
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView(){
        binding.apply {
            textviewItemName.text = itemViewModel.selectedItem.value?.name.toString()
            textviewItemExplain.text = itemViewModel.selectedItem.value?.description.toString()
            Glide.with(this.root)
                .load(itemViewModel.selectedItem.value?.imagePath)
                .into(imageItem)
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        itemViewModel.setItemClickListenerEnabled(true)
        itemViewModel.setSelectedItem(null)
    }
}