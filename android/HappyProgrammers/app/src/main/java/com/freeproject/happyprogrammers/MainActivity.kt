package com.freeproject.happyprogrammers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.freeproject.happyprogrammers.base.BaseActivity
import com.freeproject.happyprogrammers.databinding.ActivityMainBinding
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
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.loginFragment -> {
                    binding.containerActivity.setBackgroundResource(R.drawable.image_login_background)
                    binding.layoutTopbar.visibility = View.GONE
                }
                R.id.homeFragment -> {
                    binding.containerActivity.setBackgroundResource(R.drawable.image_home_background)
                    binding.layoutTopbar.visibility = View.VISIBLE
                    binding.imageLogo.visibility = View.VISIBLE
                    binding.textviewTopbar.visibility = View.GONE
                }
                else -> {
                    binding.containerActivity.setBackgroundResource(R.drawable.image_home_background)
                    binding.layoutTopbar.visibility = View.VISIBLE
                    binding.imageLogo.visibility = View.GONE
                    binding.textviewTopbar.visibility = View.VISIBLE
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

        if(mainViewModel.isLogined()){ //로그인이 되어 있는 상황
            graph.startDestination = R.id.homeFragment
        }else{ //로그인이 되어 있지 않은 상황
            graph.startDestination = R.id.loginFragment
        }
        val navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)
    }
}