package com.anshad.whetherapp.data.repositories

import com.anshad.whetherapp.model.ForecastResponse
import io.reactivex.rxjava3.core.Observable

interface MainRepository {
    fun getData(latitude: String?, longitude: String?, count: String, apiKey: String): Observable<ForecastResponse>
}