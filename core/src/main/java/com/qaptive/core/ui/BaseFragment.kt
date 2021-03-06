package com.qaptive.core.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.qaptive.core.models.Action
import com.qaptive.core.models.BaseActivityViewModel
import com.qaptive.core.models.LoadingMessageData
import com.qaptive.core.models.MessageData
import com.qaptive.core.utils.EventObserver
import com.qaptive.core.viewmodel.BaseViewModel

abstract class BaseFragment<VM : BaseViewModel>(@LayoutRes layout: Int) : Fragment(layout) {

    protected abstract val viewModel: VM

    private val activityViewModel: BaseActivityViewModel by activityViewModels()

    var isPaused = false
        private set

    private var pendingNavigationActionId = 0
    private var pendingNavigationActionBundle: Bundle? = null
    private var pendingNavigateUp = false
    private var pendingNavigationIntent: Intent? = null
    private var pendingNavigationActivityClass: Class<*>? = null
    private var pendingNavigationFinishCurrent = false
    private var pendingNavDirections: NavDirections? = null

    override fun onPause() {
        super.onPause()
        isPaused = true
    }

    override fun onResume() {
        super.onResume()
        isPaused = false
        continuePendingNavigation()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loadingMessageData = LoadingMessageData()
        loadingMessageData.isLoading = false
        onLoadingMessage(loadingMessageData)

        viewModel.loading.observe(viewLifecycleOwner, EventObserver {
            onLoadingMessage(it)
        })

        viewModel.action.observe(viewLifecycleOwner, EventObserver {
            onPerformAction(it)
        })

        viewModel.infoMessage.observe(viewLifecycleOwner, EventObserver {
            onInfoMessage(it)
        })
        activityViewModel.actionPerformed.observe(viewLifecycleOwner, EventObserver {
            onActionPerformed(it)
        })

        viewModel.navDirections.observe(viewLifecycleOwner, EventObserver {
            onNavigateAction(it)
        })

        viewModel.navigate.observe(viewLifecycleOwner, EventObserver {
            onNavigate(it.id, it.bundle)
        })

        viewModel.upNavigation.observe(viewLifecycleOwner, EventObserver {
            onNavigateUp()
        })

        viewModel.activityNavigation.observe(viewLifecycleOwner, EventObserver {
            if (it.intent != null) {
                onNavigateToActivity(it.intent!!, it.finishCurrent)
            } else {
                onNavigateToActivity(it.activityClass!!, it.finishCurrent)
            }
        })
    }

    private fun continuePendingNavigation() {
        when {
            pendingNavigationActionId != 0 -> {
                onNavigate(pendingNavigationActionId, pendingNavigationActionBundle)
            }
            pendingNavDirections != null -> {
                onNavigateAction(pendingNavDirections!!)
            }
//            pendingNavigationIntent != null -> {
//                onNavigateToActivity(pendingNavigationIntent!!, pendingNavigationFinishCurrent)
//            }
//            pendingNavigationActivityClass != null -> {
//                onNavigateToActivity(pendingNavigationActivityClass!!, pendingNavigationFinishCurrent)
//            }
            pendingNavigateUp -> {
                onNavigateUp()
            }
        }
        resentPendingState()
    }

    private fun resentPendingState() {
        pendingNavigationActionId = 0
        pendingNavigationActionBundle = null
        pendingNavigateUp = false
        pendingNavigationIntent = null
        pendingNavigationActivityClass = null
        pendingNavigationFinishCurrent = false
        pendingNavDirections = null
    }

    open fun onLoadingMessage(messageData: LoadingMessageData) {
        activityViewModel.showLoading(messageData)
    }

    open fun onInfoMessage(messageData: MessageData) {
        activityViewModel.showInfoMessage(messageData)
    }

    open fun onPerformAction(action: Action) {
        activityViewModel.performAction(action)
    }

    open fun onActionPerformed(action: Action) {
    }

    open fun onNavigate(navigationActionId: Int, bundle: Bundle? = null) {
        if (isPaused) {
            pendingNavigationActionId = navigationActionId
            pendingNavigationActionBundle = bundle
            return
        }
        findNavController().navigate(navigationActionId, bundle)
    }

    open fun onNavigateAction(navigationActionId: NavDirections) {
        if (isPaused) {
            pendingNavDirections = navigationActionId
            return
        }
        findNavController().navigate(navigationActionId)
    }

    open fun onNavigateUp() {
        if (isPaused) {
            pendingNavigateUp = true
            return
        }
        if (!findNavController().navigateUp()) {
            activity?.onBackPressed()
        }
    }

    @Deprecated("Remove")
    fun onNavigateToActivity(intent: Intent, finishCurrent: Boolean) {
        if (isPaused) {
            pendingNavigationIntent = intent
            pendingNavigationFinishCurrent = finishCurrent
            return
        }
        activity?.startActivity(intent)
        if (finishCurrent)
            activity?.finish()
    }

    @Deprecated("Remove")
    fun onNavigateToActivity(activityClass: Class<*>, finishCurrent: Boolean) {
        if (isPaused) {
            pendingNavigationActivityClass = activityClass
            pendingNavigationFinishCurrent = finishCurrent
            return
        }
        val intent = Intent(activity, activityClass)
        activity?.startActivity(intent)
        if (finishCurrent)
            activity?.finish()

    }


}