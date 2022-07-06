package com.anshad.whetherapp.api


import com.anshad.whetherapp.constants.APIUrls
import com.anshad.whetherapp.model.ForecastResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET(APIUrls.GET_WHETHER)
    fun getData(@Query("lat") latitude:String?,
                @Query("lon") longitude:String?,
                @Query("cnt") count:String,
        @Query("appid") appid:String
    ): Observable<ForecastResponse>


}