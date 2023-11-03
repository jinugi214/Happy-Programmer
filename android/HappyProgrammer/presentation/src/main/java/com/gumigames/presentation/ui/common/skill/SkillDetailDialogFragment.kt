package com.gumigames.presentation.ui.common.skill

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.freeproject.happyprogrammers.base.BaseBorderDialogFragment
import com.gumigames.domain.model.common.SkillDto
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentSkillDetailDialogBinding
import com.gumigames.presentation.ui.bookmark.BookmarkViewModel
import com.gumigames.presentation.ui.dogam.DogamViewModel
import com.gumigames.presentation.util.clickAnimation

class SkillDetailDialogFragment(
    private val dogamViewModel: DogamViewModel?,
    private val bookmarkViewModel: BookmarkViewModel?
) : BaseBorderDialogFragment<FragmentSkillDetailDialogBinding>(
    FragmentSkillDetailDialogBinding::bind,
    R.layout.fragment_skill_detail_dialog
) {

    private lateinit var skill: SkillDto

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initView()
        initListener()
    }

    private fun init(){
        skill = (dogamViewModel?.selectedSkill?.value) ?: bookmarkViewModel!!.selectedBookmarkSkill.value!!
    }

    private fun initView(){
        binding.apply {
            textviewItemName.text = skill.name.toString()
            textviewItemExplain.text = skill.description.toString()
            Glide.with(this.root)
                .load(skill.imgPath)
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
            dogamViewModel.setSelectedSkill(null)
        }else{
            bookmarkViewModel!!.setItemClickListenerEnabled(true)
            bookmarkViewModel.setSelectedBookmarkSkill(null)
        }
    }
}