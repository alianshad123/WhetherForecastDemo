package com.anshad.whetherapp

import com.qaptive.core.models.Action
import com.qaptive.core.ui.BaseActivity

abstract class BaseDemoActivity: BaseActivity() {
    override fun onPerformAction(action: Action) {
        when(action.task.action) {
            else -> {
                super.onPerformAction(action)
            }
        }
    }
}