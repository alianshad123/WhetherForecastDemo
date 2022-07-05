package com.anshad.whetherapp

import android.os.Bundle
import com.anshad.whetherapp.databinding.ActivityMainBinding
import com.qaptive.core.models.LoadingMessageData
import com.qaptive.core.models.MessageData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :  BaseDemoActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onLoadingMessage(messageData: LoadingMessageData) {
        if (messageData.context == null) {
            messageData.context = this
        }
        binding.isLoading = messageData.isLoading
        binding.message = messageData.getMessage()
    }

    override fun onInfoMessage(messageData: MessageData) {

    }

    override fun onLogout() {

    }
}