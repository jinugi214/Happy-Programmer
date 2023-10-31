package com.gumigames.presentation.ui.github

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.freeproject.happyprogrammers.base.BaseFragment
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentGithubBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "차선호"
@AndroidEntryPoint
class GithubFragment : BaseFragment<FragmentGithubBinding>(
    FragmentGithubBinding::bind,
    R.layout.fragment_github
) {

    private val githubViewModel: GithubViewModel by viewModels()
    private lateinit var githubListAdapter: GithubListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initCollect()
        collectErrorAndToken(githubViewModel)
    }

    private fun initView(){
        binding.apply {


            githubListAdapter = GithubListAdapter()
            recyclerviewSearch.apply {
                this.adapter = githubListAdapter
                layoutManager = LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false)
            }
        }
    }
    override fun initListener() {

        binding.apply {

            edittextSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(value: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(value: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun afterTextChanged(value: Editable?) {
                    githubViewModel.setUserId(value.toString())
                }

            })

            buttonSearch.setOnClickListener {
                githubViewModel.getUserRepos()
            }
        }
    }

    private fun initCollect(){
        viewLifecycleOwner.lifecycleScope.launch {
            githubViewModel.repoList.collectLatest {
                githubListAdapter.submitList(it)
            }
        }
    }
}