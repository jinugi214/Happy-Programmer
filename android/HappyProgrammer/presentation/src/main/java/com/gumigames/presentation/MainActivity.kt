package com.gumigames.presentation

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.freeproject.happyprogrammers.base.BaseActivity
import com.gumigames.presentation.databinding.ActivityMainBinding
import com.gumigames.presentation.util.PERMISSION_LIST_UNDER32
import com.gumigames.presentation.util.PERMISSION_LIST_UP33
import com.gumigames.presentation.util.createPermissionLauncher
import com.gumigames.presentation.util.showPermissionDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "차선호"
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val mainViewModel: MainViewModel by viewModels()

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()
        initNavController()
        initListener()
        initCollector()
    }
    private fun requestPermission(){
        mainViewModel.setIsAlreadyShowedDialog(false)
        permissionLauncher = createPermissionLauncher(
            fragment = null,
            activity = this,
            getPermissionRejected = {it -> mainViewModel.getPermissionRejected(it)},
            setPermissionRejected = {it -> mainViewModel.setPermissionRejected(it)},
            getIsShowedPermissionDialog = {it -> mainViewModel.getIsShowedPermissionDialog(it+"show")},
            setIsShowedPermissionDialog = {it -> mainViewModel.setIsShowedPermissionDialog(it+"show")},
            isShowDialog = {if(!mainViewModel.isShowPermissionDialog.value) mainViewModel.setIsShowPermissionDialog(true)}
        )
        permissionLauncher.launch(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) PERMISSION_LIST_UP33 else PERMISSION_LIST_UNDER32)
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
                            R.id.dogamFragment -> {textviewTopbar.text = getString(R.string.title_dogam)}
                            R.id.bookmarkFragment -> {textviewTopbar.text = getString(R.string.text_bookmark)}
                            R.id.missionFragment -> {textviewTopbar.text = getString(R.string.text_mission)}
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

    private fun initCollector(){
        mainViewModel.apply {
            lifecycleScope.launch {
                isShowPermissionDialog.collectLatest {
                    Log.d(TAG, "isShowPermissionDialog collect... $it")
                    if(it){
                        showPermissionDialog(this@MainActivity)
                        setIsShowPermissionDialog(false)
                    }
                }
            }
        }
    }
}