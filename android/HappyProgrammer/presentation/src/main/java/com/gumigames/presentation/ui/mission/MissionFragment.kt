package com.gumigames.presentation.ui.mission

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.FileProvider
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.freeproject.happyprogrammers.base.BaseFragment
import com.gumigames.presentation.MainViewModel
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentMissionBinding
import com.gumigames.presentation.util.CAMERA_PERMISSION_REJECTED
import com.gumigames.presentation.util.createCameraIntent
import com.gumigames.presentation.util.createCameraLauncher
import com.gumigames.presentation.util.createPermissionLauncher
import com.gumigames.presentation.util.createImageFile
import com.gumigames.presentation.util.createMultipartFromFile
import com.gumigames.presentation.util.hasPermissions
import com.gumigames.presentation.util.showPermissionDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.File
import java.security.Permission


private const val TAG = "차선호"
@AndroidEntryPoint
class MissionFragment: BaseFragment<FragmentMissionBinding>(
    FragmentMissionBinding::bind,
    R.layout.fragment_mission
){

    private val mainViewModel: MainViewModel by activityViewModels()
    private val missionViewModel: MissionViewModel by viewModels()
    private lateinit var file: File
    private lateinit var cameraPermissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var cameraIntent: Intent
    private lateinit var cameraLauncher: ActivityResultLauncher<Intent>
    private val missionLoadingDialogFragment = MissionLoadingDialogFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPermissionLauncher()
        initCameraLauncher()
        initListener()
        initCollect()
        collectErrorAndToken(missionViewModel)
    }


    override fun initListener() {
        binding.apply {
            //카메라 버튼 클릭
            buttonCamera.setOnClickListener {
                cameraPermissionLauncher.launch(arrayOf(CAMERA_PERMISSION_REJECTED))
                if(mActivity.hasPermissions(CAMERA_PERMISSION_REJECTED)){
                    //카메라 앱을 띄워 사진을 받아옵니다.
                    cameraIntent = createCameraIntent(
                        context = mActivity,
                        file = file
                    )
                    cameraLauncher.launch(cameraIntent) //카메라 앱을 실행 한 후 결과를 받기 위해서 launch
                }else{
                    if(mainViewModel.getIsShowedPermissionDialog(CAMERA_PERMISSION_REJECTED)){
                        showSnackbar(this.root, "info", "설정에서 카메라 권한을 허용해 주세요")
                    }
                    return@setOnClickListener
                }
            }
            // 전송 버튼 클릭
            buttonSend.setOnClickListener {
                Log.d(TAG, "전송 클릭 : ${mainViewModel.getIsConnected()}")
                if(!mainViewModel.getIsConnected()){ //네트워크 연결이 안되어 있다면
                    showSnackbar(this.root, "fail", "네트워크 연결 상태를 확안하세요")
                    return@setOnClickListener
                }
                if(missionViewModel.isPossibleSendPhoto()){
                    missionViewModel.sendPhoto()
                    //여기서 로딩 다이얼로그 띄우고
                    missionLoadingDialogFragment.isCancelable = false
                    missionLoadingDialogFragment.show(childFragmentManager, null)
                }else{
                    showSnackbar(this.root, "fail", "사진을 다시 제출해 주세요")
                }
            }
        }
    }

    private fun initCollect(){
        missionViewModel.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                multipartBody.collectLatest {
                    Log.d(TAG, "multipart : $it")
                }
            }
            viewLifecycleOwner.lifecycleScope.launch {
                resultMessage.collectLatest {
                    //여기서 닫자
                    missionLoadingDialogFragment.dismiss()
                    if(it=="성공"){
                        Log.d(TAG, "initCollect result 성공")
                        showSnackbar(binding.root, "success", "미션 성공")
                    }else{
                        Log.d(TAG, "initCollect result 실패")
                        showSnackbar(binding.root, "fail", "미션 실패")
                    }
                }
            }
            viewLifecycleOwner.lifecycleScope.launch {
                error.collectLatest {
                    Log.d(TAG, "initCollect error...")
                    missionLoadingDialogFragment.dismiss()
                }
            }
        }
    }


    private fun initPermissionLauncher(){
        cameraPermissionLauncher = createPermissionLauncher(
            fragment = this,
            activity = mActivity,
            getPermissionRejected = {it -> mainViewModel.getPermissionRejected(it)},
            setPermissionRejected = {it -> mainViewModel.setPermissionRejected(it)},
            getIsShowedPermissionDialog = {it -> mainViewModel.getIsShowedPermissionDialog(it+"show")},
            setIsShowedPermissionDialog = {it -> mainViewModel.setIsShowedPermissionDialog(it+"show")},
            isShowDialog = {
                Log.d(TAG, "missionfragment에서 ${mainViewModel.isShowPermissionDialog.value}")
                if(!mainViewModel.isShowPermissionDialog.value) mainViewModel.setIsShowPermissionDialog(true)
            }
        )
    }

    private fun initCameraLauncher(){
        //파일 생성
        file = createImageFile(mActivity)
        //카메라 launcher 생성
        cameraLauncher = createCameraLauncher(
            fragment = this@MissionFragment,
            activity = mActivity,
            file = file,
            onLoadBitmap = {binding.imageMission.setImageBitmap(it)}
        ){
            missionViewModel.setMultipartBody(
                createMultipartFromFile(file = file)
            ){
                showSnackbar(binding.root, "fail", "파일을 생성하는 데 실패했습니다. 다시 촬영해주세요")
            }
        }
    }

}