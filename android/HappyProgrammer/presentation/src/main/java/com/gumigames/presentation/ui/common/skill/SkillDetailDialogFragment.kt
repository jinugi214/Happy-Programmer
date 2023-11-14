package com.gumigames.presentation.ui.common.skill

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.freeproject.happyprogrammers.base.BaseBorderDialogFragment
import com.gumigames.domain.model.common.SkillDto
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentSkillDetailDialogBinding
import com.gumigames.presentation.ui.bookmark.BookmarkViewModel
import com.gumigames.presentation.ui.dogam.DogamViewModel
import com.gumigames.presentation.util.clickAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "차선호"
@AndroidEntryPoint
class SkillDetailDialogFragment(
    private val dogamViewModel: DogamViewModel?,
    private val bookmarkViewModel: BookmarkViewModel?
) : BaseBorderDialogFragment<FragmentSkillDetailDialogBinding>(
    FragmentSkillDetailDialogBinding::bind,
    R.layout.fragment_skill_detail_dialog
) {

    private val skillDetailDialogViewModel: SkillDetailDialogViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initView()
        initListener()
        initCollect()
        collectErrorAndToken(skillDetailDialogViewModel)
    }

    private fun init(){
        skillDetailDialogViewModel.setCurrentSkill(
            (dogamViewModel?.selectedSkill?.value) ?: bookmarkViewModel!!.selectedBookmarkSkill.value!!
        )
    }

    private fun initView(){
        binding.apply {
            textviewItemName.text = skillDetailDialogViewModel.getCurrentSkill().name.toString()
            textviewItemExplain.text = skillDetailDialogViewModel.getCurrentSkill().description.toString()
            if(skillDetailDialogViewModel.getCurrentSkill().imageBitmap!=null) imageItem.setImageBitmap(skillDetailDialogViewModel.getCurrentSkill().imageBitmap)
            else imageItem.setImageResource(R.drawable.image_tool_profile) //TODO 추후에 로딩 이미지로 바꿔라
            //TODO 아이템의 isBookmarked를 보고 분기 태워야 함
            if(skillDetailDialogViewModel.getCurrentSkill().isBookmarked){
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
                skillDetailDialogViewModel.toggleIsBookmarked()
                buttonUnselcetedBookmark.clickAnimation(viewLifecycleOwner)
                buttonSelcetedBookmark.clickAnimation(viewLifecycleOwner)
            }
            //즐겨찾기 등록 이벤트
            buttonUnselcetedBookmark.setOnClickListener {
                skillDetailDialogViewModel.toggleIsBookmarked()
                buttonUnselcetedBookmark.clickAnimation(viewLifecycleOwner)
                buttonSelcetedBookmark.clickAnimation(viewLifecycleOwner)
            }
        }
    }

    private fun initCollect(){
        skillDetailDialogViewModel.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                currentIsBookmarked.collectLatest {
                    binding.buttonSelcetedBookmark.isEnabled = false
                    binding.buttonUnselcetedBookmark.isEnabled = false
                    Log.d(TAG, "toggle 결과 : $it")
                    if(it){
                        Log.d(TAG, "true 버튼 갱신")
                        if(dogamViewModel!=null){
                            updateDogamList(
                                value = true,
                                list = dogamViewModel.getSkillListAdapterList(),
                                position = dogamViewModel.selectedSkillPosition.value!!
                            ){newList ->
                                dogamViewModel.updateSkillListAdapter(newList)
                            }
                        }else{
                            updateBookmarkList(
                                value = true,
                                list = bookmarkViewModel!!.getSkillListAdapterList(),
                                position = bookmarkViewModel.selectedSkillPosition.value!!,
                                skill = bookmarkViewModel.selectedBookmarkSkill.value
                            ){newList ->
                                bookmarkViewModel.updateSkillListAdapter(newList)
                            }
                        }
                        binding.buttonUnselcetedBookmark.visibility = View.GONE
                        binding.buttonSelcetedBookmark.visibility = View.VISIBLE
                    }else{
                        if(dogamViewModel!=null){
                            updateDogamList(
                                value = false,
                                list = dogamViewModel.getSkillListAdapterList(),
                                position = dogamViewModel.selectedSkillPosition.value!!
                            ){newList ->
                                dogamViewModel.updateSkillListAdapter(newList)
                            }
                        }else{
                            updateBookmarkList(
                                value = false,
                                list = bookmarkViewModel!!.getSkillListAdapterList(),
                                position = bookmarkViewModel.selectedSkillPosition.value!!,
                                skill = bookmarkViewModel.selectedBookmarkSkill.value
                            ){newList ->
                                bookmarkViewModel.updateSkillListAdapter(newList)
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
            dogamViewModel.setSelectedSkill(-1, null)
        }else{
            bookmarkViewModel!!.setItemClickListenerEnabled(true)
            bookmarkViewModel.setSelectedBookmarkSkill(-1, null)
        }
    }
}