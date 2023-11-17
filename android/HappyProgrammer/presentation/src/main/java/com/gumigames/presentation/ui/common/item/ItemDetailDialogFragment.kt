package com.gumigames.presentation.ui.common.item

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.freeproject.happyprogrammers.base.BaseBorderDialogFragment
import com.gumigames.domain.model.common.ItemDto
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentItemDetailDialogBinding
import com.gumigames.presentation.ui.bookmark.BookmarkViewModel
import com.gumigames.presentation.ui.dogam.DogamViewModel
import com.gumigames.presentation.ui.profile.ProfileViewModel
import com.gumigames.presentation.util.clickAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


private const val TAG = "차선호"
@AndroidEntryPoint
class ItemDetailDialogFragment(
    private val dogamViewModel: DogamViewModel?,
    private val bookmarkViewModel: BookmarkViewModel?,
    private val profileViewModel: ProfileViewModel?
) : BaseBorderDialogFragment<FragmentItemDetailDialogBinding>(
    FragmentItemDetailDialogBinding::bind,
    R.layout.fragment_item_detail_dialog
) {
    private val itemDetailDialogViewModel: ItemDetailDialogViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initView()
        initListener()
        initCollect()
        collectErrorAndToken(itemDetailDialogViewModel)
    }

    private fun init(){
        if(dogamViewModel!=null){
            itemDetailDialogViewModel.setCurrentItem(dogamViewModel.selectedItem.value!!)
        }else if(bookmarkViewModel!=null){
            itemDetailDialogViewModel.setCurrentItem(bookmarkViewModel.selectedBookmarkItem.value!!)
        }else{
            itemDetailDialogViewModel.setCurrentItem(profileViewModel!!.selectedMyItem.value!!)
        }
    }

    private fun initView(){
        binding.apply {
            textviewItemName.text = itemDetailDialogViewModel.getCurrentItem().name.toString()
            textviewItemExplain.text = itemDetailDialogViewModel.getCurrentItem().description.toString()
            if(itemDetailDialogViewModel.getCurrentItem().imageBitmap!=null) imageItem.setImageBitmap(itemDetailDialogViewModel.getCurrentItem().imageBitmap)
            else imageItem.setImageResource(R.drawable.image_tool_profile) //TODO 추후에 로딩 이미지로 바꿔라
            //TODO 아이템의 isBookmarked를 보고 분기 태워야 함
            if(itemDetailDialogViewModel.getCurrentItem().isBookmarked){
                buttonUnselcetedBookmark.visibility = View.GONE
                buttonSelcetedBookmark.visibility = View.VISIBLE
            }else{
                buttonSelcetedBookmark.visibility = View.GONE
                buttonUnselcetedBookmark.visibility = View.VISIBLE
            }
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
                itemDetailDialogViewModel.toggleIsBookmarked()
                buttonUnselcetedBookmark.clickAnimation(viewLifecycleOwner)
                buttonSelcetedBookmark.clickAnimation(viewLifecycleOwner)
            }
            //즐겨찾기 등록 이벤트
            buttonUnselcetedBookmark.setOnClickListener {
                itemDetailDialogViewModel.toggleIsBookmarked()
                buttonUnselcetedBookmark.clickAnimation(viewLifecycleOwner)
                buttonSelcetedBookmark.clickAnimation(viewLifecycleOwner)
            }
        }
    }

    private fun initCollect(){
        itemDetailDialogViewModel.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                currentIsBookmarked.collectLatest { it ->
                    binding.buttonSelcetedBookmark.isEnabled = false
                    binding.buttonUnselcetedBookmark.isEnabled = false
                    Log.d(TAG, "toggle 결과 : $it")
                    if(it){
                        Log.d(TAG, "true 버튼 갱신")
                        if(dogamViewModel!=null){
                            updateDogamList(
                                value = true,
                                list = dogamViewModel.getItemListAdapterList(),
                                position = dogamViewModel.selectedItemPosition.value!!
                            ){newList ->
                                dogamViewModel.updateItemListAdapter(newList)
                            }
                        }else if(bookmarkViewModel!=null){
                            updateBookmarkList(
                                value = true,
                                list = bookmarkViewModel.getItemListAdapterList(),
                                position = bookmarkViewModel.selectedItemPosition.value!!,
                                item = bookmarkViewModel.selectedBookmarkItem.value
                            ){newList ->
                                bookmarkViewModel.updateItemListAdapter(newList)
                            }
                        }else{
                            updateDogamList(
                                value = true,
                                list = profileViewModel!!.getItemListAdapterList(),
                                position = profileViewModel.selectedMyItemPosition.value!!
                            ){newList ->
                                profileViewModel.updateItemListAdapter(newList)
                            }
                        }
                        binding.buttonUnselcetedBookmark.visibility = View.GONE
                        binding.buttonSelcetedBookmark.visibility = View.VISIBLE
                    }else{
                        if(dogamViewModel!=null){
                            updateDogamList(
                                value = false,
                                list = dogamViewModel.getItemListAdapterList(),
                                position = dogamViewModel.selectedItemPosition.value!!
                            ){newList ->
                                dogamViewModel.updateItemListAdapter(newList)
                            }
                        }else if(bookmarkViewModel!=null){
                            updateBookmarkList(
                                value = false,
                                list = bookmarkViewModel.getItemListAdapterList(),
                                position = bookmarkViewModel.selectedItemPosition.value!!,
                                item = null
                            ){newList ->
                                bookmarkViewModel.updateItemListAdapter(newList)
                            }
                        }else{
                            updateDogamList(
                                value = false,
                                list = profileViewModel!!.getItemListAdapterList(),
                                position = profileViewModel.selectedMyItemPosition.value!!
                            ){newList ->
                                profileViewModel.updateItemListAdapter(newList)
                            }
                        }
                        Log.d(TAG, "false 버튼 갱신")
                        binding.buttonSelcetedBookmark.visibility = View.GONE
                        binding.buttonUnselcetedBookmark.visibility = View.VISIBLE
                    }
                    binding.buttonSelcetedBookmark.isEnabled = true
                    binding.buttonUnselcetedBookmark.isEnabled = true
                }
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if(dogamViewModel != null) {
            dogamViewModel.setItemClickListenerEnabled(true)
            dogamViewModel.setSelectedItem(-1, null)
        }else if(bookmarkViewModel!=null){
            bookmarkViewModel.setItemClickListenerEnabled(true)
            bookmarkViewModel.setSelectedBookmarkItem(-1, null)
        }else{
            profileViewModel!!.setItemClickListenerEnabled(true)
            profileViewModel.setSelectedMyItem(-1, null)
        }
    }
}
