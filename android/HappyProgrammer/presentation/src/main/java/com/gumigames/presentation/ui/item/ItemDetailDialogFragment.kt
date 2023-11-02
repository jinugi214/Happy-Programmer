package com.gumigames.presentation.ui.item

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.freeproject.happyprogrammers.base.BaseDialogFragment
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentItemDetailDialogBinding

class ItemDetailDialogFragment(
    private val itemViewModel: ItemViewModel
) : BaseDialogFragment<FragmentItemDetailDialogBinding>(
    FragmentItemDetailDialogBinding::bind,
    R.layout.fragment_item_detail_dialog
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        itemViewModel.setItemClickListenerEnabled(true)
    }
}