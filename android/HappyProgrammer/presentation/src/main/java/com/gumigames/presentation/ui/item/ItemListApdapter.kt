package com.gumigames.presentation.ui.item

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gumigames.domain.model.item.ItemDto
import com.gumigames.presentation.BuildConfig
import com.gumigames.presentation.databinding.ItemItemBinding

private const val TAG = "차선호"
class ItemListApdapter: ListAdapter<ItemDto, ItemListApdapter.ItemListHolder>(
    ItemListComparator
) {
    companion object ItemListComparator : DiffUtil.ItemCallback<ItemDto>() {
        override fun areItemsTheSame(oldItem: ItemDto, newItem: ItemDto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ItemDto, newItem: ItemDto): Boolean {
//            return oldItem.id == newItem.id
            return oldItem.name == newItem.name
        }
    }
    inner class ItemListHolder(private val binding: ItemItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bindInfo(item : ItemDto){
            Log.d(TAG, "bindInfo.... $item")
            binding.apply {
                Glide.with(binding.root)
                    .load(BuildConfig.BASE_URL+item.imagePath)
                    .into(imageItem)
                imageItem.setOnClickListener {
                    itemClickListner.onClick(it, item)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListHolder {
        val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemListHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemListHolder, position: Int) {
        holder.apply {
            bindInfo(getItem(position))
        }
    }

    //클릭 인터페이스 정의 사용하는 곳에서 만들어준다.
    interface ItemClickListener {
        fun onClick(view: View, item: ItemDto)
    }
    //클릭리스너 선언
    lateinit var itemClickListner: ItemClickListener
}