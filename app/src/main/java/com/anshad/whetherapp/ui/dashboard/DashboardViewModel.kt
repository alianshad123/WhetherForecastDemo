package com.anshad.whetherapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anshad.whetherapp.DemoViewmodel
import com.anshad.whetherapp.data.repositories.MainRepository
import com.anshad.whetherapp.model.City
import com.anshad.whetherapp.model.ForecastResponse
import com.anshad.whetherapp.model.ListData
import com.anshad.whetherapp.utils.DateHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import org.json.JSONException
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: MainRepository,
) : DemoViewmodel() {


    val weatherListLiveData =
        MutableLiveData<List<ListData>>()
    var weatherListData: LiveData<List<ListData>> =
        weatherListLiveData

    val cityData = MutableLiveData<City>()
    val updatedTime = MutableLiveData<String>()





    init {
        getData()
    }

    fun getData() {
        showLoading()
        repository.getData().subscribe({
            onResponse(it)
        },{
            onFail(it)
        }).add()
    }

    private fun onResponse(result: ForecastResponse) {
        hideLoading()
        try {
            cityData.postValue(result.city)
            updatedTime.postValue(getCurrentTime())
            weatherListLiveData.postValue(result.list)

        }catch (ex:JSONException){

        }
    }

    private fun getCurrentTime(): String? {
        return DateHelper.getDateTime(System.currentTimeMillis()/1000)
    }

    private fun onFail(ex: Throwable) {
        hideLoading()

    }
}