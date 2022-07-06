package com.anshad.whetherapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.anshad.whetherapp.App
import com.anshad.whetherapp.DemoViewmodel
import com.anshad.whetherapp.R
import com.anshad.whetherapp.data.localdata.db.DataBaseHandler
import com.anshad.whetherapp.data.localdata.pref.PreferenceProvider
import com.anshad.whetherapp.data.repositories.MainRepository
import com.anshad.whetherapp.model.ForecastResponse
import com.anshad.whetherapp.model.ListData
import com.anshad.whetherapp.utils.DateHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONException
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: MainRepository,
    private val preferenceProvider: PreferenceProvider
) : DemoViewmodel() {


    var longitude: String?=null
    var latitude: String?=null
    val cityData = MutableLiveData<String>()
    val updatedTime = MutableLiveData<String>()
    val apiKey= App.instance.getString(R.string.wether_api)
    val count="16"

    val weatherListLiveData =
        MutableLiveData<List<ListData>>()
    var weatherListData: LiveData<List<ListData>> =
        weatherListLiveData



    fun getData(latitude: String?, longitude: String?, count: String, apiKey: String) {
        showLoading()
        repository.getData(latitude,longitude,count,apiKey).subscribe({
            onResponse(it)
        },{
            onFail(it)
        }).add()
    }

    private fun onResponse(result: ForecastResponse) {
        hideLoading()
        try {
            cityData.postValue(result.city?.name)
            updatedTime.postValue("Last Updated at :"+getCurrentTime())
            preferenceProvider.setCityName(result.city?.name)
            preferenceProvider.setLastUpdatedTime(getCurrentTime())

            viewModelScope.launch {
                try {
                    DataBaseHandler.instance.deleteAll()
                }catch (ex:Exception){
                   ex.printStackTrace()
                }

            }



            viewModelScope.launch {
                try {
                    val isData=  DataBaseHandler.instance.addWeatherData(result.list)

                    if(isData) {
                        viewModelScope.launch {
                            try {
                                var weatherList: List<ListData> =
                                    DataBaseHandler.instance.getWeatherData()
                                weatherListLiveData.postValue(weatherList)
                            } catch (e: Exception) {
                                // handler error
                            }
                        }
                    }


                } catch (e: Exception) {
                    // handler error
                }
            }


        }catch (ex:JSONException){

        }
    }

    private fun getCurrentTime(): String? {
        return DateHelper.getDateTime(System.currentTimeMillis()/1000)
    }

    private fun onFail(ex: Throwable) {
        hideLoading()

        cityData.postValue(preferenceProvider.getCityName())
        updatedTime.postValue("Last Updated at :"+preferenceProvider.getLastUpdatedTime())

        viewModelScope.launch {
            try {
                var weatherList: List<ListData> =
                    DataBaseHandler.instance.getWeatherData()
                weatherListLiveData.postValue(weatherList)
            } catch (e: Exception) {
                // handler error
            }
        }
        showNetworkError(negativeButton = R.string.cancel_) {

        }

    }
}