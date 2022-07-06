package com.anshad.whetherapp.data.datasource

import com.anshad.whetherapp.api.ApiService
import com.anshad.whetherapp.data.repositories.MainRepository
import com.anshad.whetherapp.model.ForecastResponse
import com.qaptive.core.ktx.Observable.applyNetworkSchedulers
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MainDataSource @Inject constructor(private val apiService: ApiService
) : MainRepository {
    override fun getData(latitude: String?, longitude: String?, count: String, apiKey: String): Observable<ForecastResponse> {
        return apiService.getData(latitude,longitude,count,apiKey).applyNetworkSchedulers()
    }
}