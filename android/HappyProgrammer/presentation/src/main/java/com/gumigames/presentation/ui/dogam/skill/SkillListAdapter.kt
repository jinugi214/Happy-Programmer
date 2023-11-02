package com.gumigames.presentation.ui.dogam.skill

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gumigames.domain.model.item.MonsterDto
import com.gumigames.domain.model.item.SkillDto
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
                Glide.with(binding.root)
                    .load(skill.imgPath)
                    .into(imageItem)
                imageItem.setOnClickListener {
                    itemClickListner.onClick(it, skill)
                }
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