package com.gumigames.presentation.ui.common.monster

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.freeproject.happyprogrammers.base.BaseBorderDialogFragment
import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentMonsterDetailDialogBinding
import com.gumigames.presentation.ui.bookmark.BookmarkViewModel
import com.gumigames.presentation.ui.dogam.DogamViewModel
import com.gumigames.presentation.util.clickAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "차선호"
@AndroidEntryPoint
class MonsterDetailDialogFragment(
    private val dogamViewModel: DogamViewModel?,
    private val bookmarkViewModel: BookmarkViewModel?
) : BaseBorderDialogFragment<FragmentMonsterDetailDialogBinding>(
    FragmentMonsterDetailDialogBinding::bind,
    R.layout.fragment_monster_detail_dialog
) {

    private val monsterDetailDialogViewModel: MonsterDetailDialogViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initView()
        initListener()
        initCollect()
        collectErrorAndToken(monsterDetailDialogViewModel)
    }

    private fun init(){
        monsterDetailDialogViewModel.setCurrentMonster(
            (dogamViewModel?.selectedMonster?.value) ?: bookmarkViewModel!!.selectedBookmarkMonster.value!!
        )
    }

    private fun initView(){
        binding.apply {
            textviewItemName.text = monsterDetailDialogViewModel.getCurrentMonster().name.toString()
            textviewItemExplain.text = monsterDetailDialogViewModel.getCurrentMonster().description.toString()
            if(monsterDetailDialogViewModel.getCurrentMonster().imageBitmap!=null) imageItem.setImageBitmap(monsterDetailDialogViewModel.getCurrentMonster().imageBitmap)
            else imageItem.setImageResource(R.drawable.image_tool_profile) //TODO 추후에 로딩 이미지로 바꿔라
            //TODO 아이템의 isBookmarked를 보고 분기 태워야 함
            if(monsterDetailDialogViewModel.getCurrentMonster().isBookmarked){
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
                monsterDetailDialogViewModel.toggleIsBookmarked()
                buttonUnselcetedBookmark.clickAnimation(viewLifecycleOwner)
                buttonSelcetedBookmark.clickAnimation(viewLifecycleOwner)
            }
            //즐겨찾기 등록 이벤트
            buttonUnselcetedBookmark.setOnClickListener {
                monsterDetailDialogViewModel.toggleIsBookmarked()
                buttonUnselcetedBookmark.clickAnimation(viewLifecycleOwner)
                buttonSelcetedBookmark.clickAnimation(viewLifecycleOwner)
            }
        }
    }

    private fun initCollect(){
        monsterDetailDialogViewModel.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                currentIsBookmarked.collectLatest {
                    Log.d(TAG, "toggle 결과 : $it")
                    if(it){
                        Log.d(TAG, "true 버튼 갱신")
                        binding.buttonUnselcetedBookmark.visibility = View.GONE
                        binding.buttonSelcetedBookmark.visibility = View.VISIBLE
                    }else{
                        Log.d(TAG, "false 버튼 갱신")
                        binding.buttonSelcetedBookmark.visibility = View.GONE
                        binding.buttonUnselcetedBookmark.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if(dogamViewModel != null) {
            dogamViewModel.setItemClickListenerEnabled(true)
            dogamViewModel.setSelectedMonster(null)
        }else{
            bookmarkViewModel!!.setItemClickListenerEnabled(true)
            bookmarkViewModel.setSelectedBookmarkMonster(null)
        }
    }
}