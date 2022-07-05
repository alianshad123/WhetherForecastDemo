package com.anshad.whetherapp.ui.splash

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

        navigate(R.id.action_splashFragment_to_dashboardFragment)


    }
}