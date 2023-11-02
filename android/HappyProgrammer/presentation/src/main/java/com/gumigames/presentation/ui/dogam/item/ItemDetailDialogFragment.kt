package com.gumigames.presentation.ui.dogam.item

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.freeproject.happyprogrammers.base.BaseDialogFragment
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentItemDetailDialogBinding
import com.gumigames.presentation.ui.dogam.DogamViewModel

class ItemDetailDialogFragment(
    private val dogamViewModel: DogamViewModel
) : BaseDialogFragment<FragmentItemDetailDialogBinding>(
    FragmentItemDetailDialogBinding::bind,
    R.layout.fragment_item_detail_dialog
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    private fun initView(){
        binding.apply {
            textviewItemName.text = dogamViewModel.selectedItem.value?.name.toString()
            textviewItemExplain.text = dogamViewModel.selectedItem.value?.description.toString()
            Glide.with(this.root)
                .load(dogamViewModel.selectedItem.value?.imagePath)
                .into(imageItem)
            //TODO 아이템의 isBookmarked를 보고 분기 태워야 함
            buttonSelcetedBookmark.visibility = View.GONE
        }
    }

    private fun initListener(){
        binding.apply {
            //확인 버튼 이벤트
            buttonConfirm.setOnClickListener {
                dismiss()
            }
            //즐겨찾기 해제 이벤트
            buttonSelcetedBookmark.setOnClickListener {
                buttonSelcetedBookmark.visibility = View.GONE
                buttonUnselcetedBookmark.visibility = View.VISIBLE
            }
            //즐겨찾기 등록 이벤트
            buttonUnselcetedBookmark.setOnClickListener {
                buttonUnselcetedBookmark.visibility = View.GONE
                buttonSelcetedBookmark.visibility = View.VISIBLE
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        dogamViewModel.setItemClickListenerEnabled(true)
        dogamViewModel.setSelectedItem(null)
    }
}