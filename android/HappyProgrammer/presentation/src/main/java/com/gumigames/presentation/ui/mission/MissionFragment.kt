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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.freeproject.happyprogrammers.base.BaseFragment
import com.gumigames.presentation.R
import com.gumigames.presentation.databinding.FragmentMissionBinding
import com.gumigames.presentation.util.CAMERA_PERMISSION_REJECTED
import com.gumigames.presentation.util.createImageFile
import com.gumigames.presentation.util.createMultipartFromFile
import com.gumigames.presentation.util.hasPermissions
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

    private val missionViewModel: MissionViewModel by viewModels()
    private lateinit var file: File


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initCollect()
    }


    override fun initListener() {
        binding.apply {
            //카메라 버튼 클릭
            buttonCamera.setOnClickListener {
                if(mActivity.hasPermissions(CAMERA_PERMISSION_REJECTED)){
                    /**
                     * 카메라 앱을 띄워 사진을 받아옵니다.
                     */
                    file = createImageFile(mActivity)
                    //AndroidMenifest에 설정된 URI와 동일한 값으로 설정한다.
                    val photoUri =
                        FileProvider.getUriForFile(mActivity, "com.gumigames.happyprogrammer.fileprovider", file)
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                    cameraActivityResult.launch(intent) //카메라 앱을 실행 한 후 결과를 받기 위해서 launch
                }else{
                    showCustomToast("설정에서 카메라 권한을 허용해 주세요")
                    return@setOnClickListener
                }
            }
            // 전송 버튼 클릭
            buttonSend.setOnClickListener {
                if(missionViewModel.isPossibleSendPhoto()){
                    missionViewModel.sendPhoto()
                }else{
                    showCustomToast("사진을 다시 제출해 주세요")
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
                    showCustomToast(it)
                }
            }
        }
    }


    private val cameraActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                var bitmap: Bitmap

                if (Build.VERSION.SDK_INT >= 29) {
                    val source: ImageDecoder.Source = ImageDecoder.createSource(
                        requireContext().contentResolver,
                        Uri.fromFile(file)
                    )
                    Log.d(TAG, "source : $source")
                    bitmap = ImageDecoder.decodeBitmap(source)

                } else {
                    bitmap = MediaStore.Images.Media.getBitmap(
                        requireContext().contentResolver,
                        Uri.fromFile(file)
                    )
                }
                binding.imageMission.setImageBitmap(bitmap)
                missionViewModel.setMultipartBody(
                    createMultipartFromFile(file = file)
                ){
                    showCustomToast("파일을 생성하는 데 실패했습니다. 다시 촬영해주세요")
                }
            }
        }

}