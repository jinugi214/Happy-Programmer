package com.gumigames.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.freeproject.happyprogrammers.base.BaseActivity
import com.gumigames.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavController()
        initListener()
    }

    private fun initListener(){

        binding.apply {
            buttonBack.setOnClickListener {
                navController.popBackStack()
            }
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.apply {
                when (destination.id) {
                    R.id.loginFragment -> {
                        containerActivity.setBackgroundResource(R.drawable.image_login_background)
                        layoutTopbar.visibility = View.GONE
                    }

                    R.id.homeFragment -> {
                        containerActivity.setBackgroundResource(R.drawable.image_home_background)
                        buttonBack.visibility = View.GONE
                        layoutTopbar.visibility = View.VISIBLE
                        imageLogo.visibility = View.VISIBLE
                        textviewTopbar.visibility = View.GONE
                    }

                    else -> {
                        containerActivity.setBackgroundResource(R.drawable.image_home_background)
                        layoutTopbar.visibility = View.VISIBLE
                        imageLogo.visibility = View.GONE
                        textviewTopbar.visibility = View.VISIBLE
                        buttonBack.visibility = View.VISIBLE
                        when (destination.id) {
                            R.id.profileFragment -> { textviewTopbar.text = getString(R.string.title_profile) }
                            R.id.itemFragment -> {textviewTopbar.text = getString(R.string.title_item)}
                        }
                    }
                }
            }
        }
    }

    private fun initNavController() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)
        graph.startDestination = R.id.homeFragment

        if(mainViewModel.isLogined()){ //로그인이 되어 있는 상황
            graph.startDestination = R.id.homeFragment
        }else{ //로그인이 되어 있지 않은 상황
            graph.startDestination = R.id.loginFragment
        }

        val navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)
    }
}