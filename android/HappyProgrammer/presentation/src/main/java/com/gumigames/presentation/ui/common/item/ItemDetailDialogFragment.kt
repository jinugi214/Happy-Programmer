package com.gumigames.presentation.ui.common.item

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.freeproject.happyprogrammers.base.BaseDialogFragment
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentItemDetailDialogBinding
import com.gumigames.presentation.ui.bookmark.BookmarkViewModel
import com.gumigames.presentation.ui.dogam.DogamViewModel
import com.gumigames.presentation.util.clickAnimation

class ItemDetailDialogFragment(
    private val dogamViewModel: DogamViewModel?,
    private val bookmarkViewModel: BookmarkViewModel?
) : BaseDialogFragment<FragmentItemDetailDialogBinding>(
    FragmentItemDetailDialogBinding::bind,
    R.layout.fragment_item_detail_dialog
) {

    private lateinit var item: ItemDto

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initView()
        initListener()
    }

    private fun init(){
        item = (dogamViewModel?.selectedItem?.value) ?: bookmarkViewModel!!.selectedBookmarkItem.value!!
    }

    private fun initView(){
        binding.apply {
            textviewItemName.text = item.name.toString()
            textviewItemExplain.text = item.description.toString()
            Glide.with(this.root)
                .load(item.imgPath)
                .into(imageItem)
            //TODO 아이템의 isBookmarked를 보고 분기 태워야 함
            buttonSelcetedBookmark.visibility = View.GONE
        }
    }

    private fun initListener(){
        binding.apply {
            //확인 버튼 이벤트
            buttonConfirm.setOnClickListener {
                it.clickAnimation(viewLifecycleOwner)
                dismiss()
            }
            //즐겨찾기 해제 이벤트
            buttonSelcetedBookmark.setOnClickListener {
                buttonUnselcetedBookmark.clickAnimation(viewLifecycleOwner)
                buttonSelcetedBookmark.clickAnimation(viewLifecycleOwner)
                buttonSelcetedBookmark.visibility = View.GONE
                buttonUnselcetedBookmark.visibility = View.VISIBLE
            }
            //즐겨찾기 등록 이벤트
            buttonUnselcetedBookmark.setOnClickListener {
                buttonUnselcetedBookmark.clickAnimation(viewLifecycleOwner)
                buttonSelcetedBookmark.clickAnimation(viewLifecycleOwner)
                buttonUnselcetedBookmark.visibility = View.GONE
                buttonSelcetedBookmark.visibility = View.VISIBLE
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if(dogamViewModel != null) {
            dogamViewModel.setItemClickListenerEnabled(true)
            dogamViewModel.setSelectedItem(null)
        }else{
            bookmarkViewModel!!.setItemClickListenerEnabled(true)
            bookmarkViewModel.setSelectedBookmarkItem(null)
        }
    }
}
