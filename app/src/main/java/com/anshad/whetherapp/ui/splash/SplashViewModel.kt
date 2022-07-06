package com.anshad.whetherapp.ui.splash

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anshad.whetherapp.DemoViewmodel
import com.anshad.whetherapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : DemoViewmodel() {

    var liveData: MutableLiveData<SplashModel> = MutableLiveData()

    var currentLatitude:Double?=0.0
    var currentLongitude:Double?=0.0
    class SplashModel {

    }

    fun initSplashScreen() {
        viewModelScope.launch {
            delay(2000)
            updateLiveData()
        }
    }


    private fun updateLiveData() {
        val splashModel = SplashModel()
        liveData.value = splashModel
    }

    fun navigateDashboard() {
        val bundle=Bundle()
        bundle.putString("latitude",currentLatitude.toString())
        bundle.putString("longitude",currentLongitude.toString())
        navigate(R.id.action_splashFragment_to_dashboardFragment,bundle)


    }
}