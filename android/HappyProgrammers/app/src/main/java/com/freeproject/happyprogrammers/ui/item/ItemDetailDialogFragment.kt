package com.freeproject.happyprogrammers.ui.item

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freeproject.happyprogrammers.R
import com.freeproject.happyprogrammers.base.BaseDialogFragment
import com.freeproject.happyprogrammers.databinding.FragmentItemDetailDialogBinding

private const val TAG = "차선호"
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