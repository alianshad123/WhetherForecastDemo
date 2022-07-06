package com.anshad.whetherapp.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.anshad.whetherapp.R
import com.anshad.whetherapp.adapter.WhetherAdapter
import com.anshad.whetherapp.databinding.FragmentDashboardBinding
import com.anshad.whetherapp.model.ListData
import com.anshad.whetherapp.utils.ItemViewPosClickCallback
import com.qaptive.core.ktx.FragmentKtx.viewBinding
import com.qaptive.core.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<DashboardViewModel>(R.layout.fragment_dashboard) {

    private val binder by viewBinding(FragmentDashboardBinding::bind)

    override val viewModel: DashboardViewModel by viewModels()

    private val adapter: WhetherAdapter by lazy {
        WhetherAdapter(object : ItemViewPosClickCallback<ListData> {

            override fun onViewClick(
                view: View?,
                item: ListData,
                position: Int,
                status: Boolean
            ) {

            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binder.viewmodel = viewModel
        binder.adapter=adapter

        viewModel.latitude= arguments?.getString("latitude") as String?
        viewModel.longitude= arguments?.getString("longitude") as String?

        viewModel.getData(viewModel.latitude,viewModel.longitude,viewModel.count,viewModel.apiKey)

        viewModel.weatherListData.observe(viewLifecycleOwner, Observer {

            if(it?.isNotEmpty() == true){
                adapter.setItems(it)

            }else{
                adapter.setItems(it)
            }
        })

    }


}