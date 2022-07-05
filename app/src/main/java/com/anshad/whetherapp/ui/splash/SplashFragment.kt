package com.anshad.whetherapp.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.anshad.whetherapp.R
import com.qaptive.core.constants.Actions
import com.qaptive.core.models.Action
import com.qaptive.core.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashFragment : BaseFragment<SplashViewModel>(R.layout.fragment_splash) {
    override val viewModel: SplashViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSplashLiveData()
    }

    private fun observeSplashLiveData() {
        viewModel.initSplashScreen()
        val observer = Observer<SplashViewModel.SplashModel> {
            viewModel.navigateDashboard()
        }
        viewModel.liveData.observe(viewLifecycleOwner, observer)
    }

    override fun onResume() {
        super.onResume()
        viewModel.performAction(Action(Intent(Actions.ENTER_FULLSCREEN)))
    }

    override fun onPause() {
        super.onPause()
        viewModel.performAction(Action(Intent(Actions.EXIT_FULLSCREEN)))
    }



}