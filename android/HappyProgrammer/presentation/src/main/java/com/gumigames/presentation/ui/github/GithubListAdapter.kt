package com.gumigames.presentation.ui.github

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.RepoDto
import com.gumigames.presentation.databinding.ItemGithubBinding

private const val TAG = "차선호"
class GithubListAdapter: ListAdapter<RepoDto, GithubListAdapter.RepoListHolder>(
    GithubListComparator
) {
    companion object GithubListComparator : DiffUtil.ItemCallback<RepoDto>() {
        override fun areItemsTheSame(oldItem: RepoDto, newItem: RepoDto): Boolean {
            Log.d(TAG, "areItemsTheSame...")
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: RepoDto, newItem: RepoDto): Boolean {
            Log.d(TAG, "areContentsTheSame...")
            return oldItem.id  == newItem.id
        }
    }

    inner class RepoListHolder(binding: ItemGithubBinding) : RecyclerView.ViewHolder(binding.root){
        val textviewName = binding.textviewName
        val textviewUrl = binding.textviewUrl
        fun bindInfo(repo : RepoDto){
            textviewName.text = repo.name
            textviewUrl.text = repo.url
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListHolder {
        Log.d(TAG, "onCreateViewHolder...")
        val binding = ItemGithubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoListHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoListHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder....")
        holder.apply {
            bindInfo(getItem(position))
        }
    }
}