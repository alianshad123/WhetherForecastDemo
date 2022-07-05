package com.anshad.whetherapp.model

import com.anshad.whetherapp.utils.DateHelper
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ListData {
    @SerializedName("dt")
    @Expose
    var dt :Long= 0
    @SerializedName("main")
    @Expose
    var main: Main? = null
    @SerializedName("weather")
    @Expose
    var weather: ArrayList<Weather>? = null
    @SerializedName("clouds")
    @Expose
    var clouds: Clouds? = null
    @SerializedName("wind")
    @Expose
    var wind: Wind? = null
    @SerializedName("visibility")
    @Expose
    var visibility = 0
    @SerializedName("pop")
    @Expose
    var pop = 0.0
    @SerializedName("rain")
    @Expose
    var rain: Rain? = null
    @SerializedName("sys")
    @Expose
    var sys: Sys? = null
    @SerializedName("dt_txt")
    @Expose
    var dt_txt: String? = null

    fun getDate(): String? {
       return DateHelper.getDateTime(dt)
    }

    fun getDateOnly(): String? {
        return DateHelper.getDateOnly(dt)
    }
}