package com.gumigames.presentation.ui.common.monster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gumigames.domain.model.common.MonsterDto
import com.gumigames.presentation.databinding.ItemDogamBinding

class MonsterListAdapter: ListAdapter<MonsterDto, MonsterListAdapter.MonsterListHolder>(
    MonsterListComparator
) {
    companion object MonsterListComparator : DiffUtil.ItemCallback<MonsterDto>() {
        override fun areItemsTheSame(oldItem: MonsterDto, newItem: MonsterDto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MonsterDto, newItem: MonsterDto): Boolean {
//            return oldItem.id == newItem.id
            return oldItem.name == newItem.name
        }
    }
    inner class MonsterListHolder(private val binding: ItemDogamBinding): RecyclerView.ViewHolder(binding.root){
        fun bindInfo(monster : MonsterDto){
            binding.apply {
                Glide.with(binding.root)
                    .load(monster.imgPath)
                    .into(imageItem)
                imageItem.setOnClickListener {
                    itemClickListner.onClick(it, monster)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonsterListHolder {
        val binding = ItemDogamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MonsterListHolder(binding)
    }

    override fun onBindViewHolder(holder: MonsterListHolder, position: Int) {
        holder.apply {
            bindInfo(getItem(position))
        }
    }

    //클릭 인터페이스 정의 사용하는 곳에서 만들어준다.
    interface ItemClickListener {
        fun onClick(view: View, monster: MonsterDto)
    }
    //클릭리스너 선언
    lateinit var itemClickListner: ItemClickListener
}