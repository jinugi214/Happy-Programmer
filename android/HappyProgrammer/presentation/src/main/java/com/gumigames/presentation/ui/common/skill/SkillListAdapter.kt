package com.gumigames.presentation.ui.common.skill

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gumigames.domain.model.common.SkillDto
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.ItemDogamBinding

class SkillListAdapter: ListAdapter<SkillDto, SkillListAdapter.SkillrListHolder>(
    SkillListComparator
) {
    companion object SkillListComparator : DiffUtil.ItemCallback<SkillDto>() {
        override fun areItemsTheSame(oldItem: SkillDto, newItem: SkillDto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SkillDto, newItem: SkillDto): Boolean {
//            return oldItem.id == newItem.id
            return oldItem.name == newItem.name
        }
    }
    inner class SkillrListHolder(private val binding: ItemDogamBinding): RecyclerView.ViewHolder(binding.root){
        fun bindInfo(skill : SkillDto){
            binding.apply {
                if(skill.imageBitmap!=null) imageItem.setImageBitmap(skill.imageBitmap)
                else imageItem.setImageResource(R.drawable.image_tool_profile) //TODO 추후에 로딩 이미지로 바꿔라
                imageItem.setOnClickListener {
                    itemClickListner.onClick(it, skill)
                }
                if(skill.isBookmarked) imageBookmark.visibility = View.VISIBLE
                else imageBookmark.visibility = View.GONE
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillrListHolder {
        val binding = ItemDogamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SkillrListHolder(binding)
    }

    override fun onBindViewHolder(holder: SkillrListHolder, position: Int) {
        holder.apply {
            bindInfo(getItem(position))
        }
    }

    //클릭 인터페이스 정의 사용하는 곳에서 만들어준다.
    interface ItemClickListener {
        fun onClick(view: View, skill: SkillDto)
    }
    //클릭리스너 선언
    lateinit var itemClickListner: ItemClickListener
}