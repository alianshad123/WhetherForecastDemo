package com.anshad.whetherapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ForecastResponse {
    @SerializedName("cod")
    @Expose
    var cod: String? = null
    @SerializedName("message")
    @Expose
    var message = 0
    @SerializedName("cnt")
    @Expose
    var cnt = 0
    @SerializedName("list")
    @Expose
    var list: ArrayList<ListData>? = null
    @SerializedName("city")
    @Expose
    var city: City? = null
}