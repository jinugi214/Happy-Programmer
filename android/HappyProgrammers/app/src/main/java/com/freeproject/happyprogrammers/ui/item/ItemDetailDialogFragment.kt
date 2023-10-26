package com.freeproject.happyprogrammers.ui.item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.freeproject.happyprogrammers.R
import com.freeproject.happyprogrammers.base.BaseDialogFragment
import com.freeproject.happyprogrammers.databinding.FragmentItemDetailDialogBinding

class ItemDetailDialogFragment : BaseDialogFragment<FragmentItemDetailDialogBinding>(
    FragmentItemDetailDialogBinding::bind,
    R.layout.fragment_item_detail_dialog
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}