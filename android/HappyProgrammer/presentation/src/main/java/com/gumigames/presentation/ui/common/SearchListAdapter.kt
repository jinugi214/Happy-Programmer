package com.gumigames.presentation.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gumigames.presentation.databinding.ItemStringBinding


private const val TAG = "차선호"
class SearchListAdapter: ListAdapter<String, SearchListAdapter.SearchListHolder>(
    SearchListComparator
) {
    companion object SearchListComparator : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
    inner class SearchListHolder(private val binding: ItemStringBinding): RecyclerView.ViewHolder(binding.root){
        fun bindInfo(text : String){
            binding.apply {
                textviewSearch.text = text
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListHolder {
        val binding = ItemStringBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchListHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchListHolder, position: Int) {
        holder.apply {
            bindInfo(getItem(position))
        }
    }

    //클릭 인터페이스 정의 사용하는 곳에서 만들어준다.
    interface ItemClickListener {
        fun onClick(view: View, text: String)
    }
    //클릭리스너 선언
    lateinit var itemClickListner: ItemClickListener
}