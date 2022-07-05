package com.anshad.whetherapp.api


import com.anshad.whetherapp.constants.APIUrls
import com.anshad.whetherapp.model.ForecastResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET


interface ApiService {

    @GET(APIUrls.GET_WHETHER)
    fun getData(): Observable<ForecastResponse>


}