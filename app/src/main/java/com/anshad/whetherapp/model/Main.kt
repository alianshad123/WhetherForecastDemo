package com.anshad.whetherapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main {
    @SerializedName("temp")
    @Expose
    var temp = 0.0
    @SerializedName("feels_like")
    @Expose
    var feels_like = 0.0
    @SerializedName("temp_min")
    @Expose
    var temp_min = 0.0
    @SerializedName("temp_max")
    @Expose
    var temp_max = 0.0
    @SerializedName("pressure")
    @Expose
    var pressure = 0
    @SerializedName("sea_level")
    @Expose
    var sea_level = 0
    @SerializedName("grnd_level")
    @Expose
    var grnd_level = 0
    @SerializedName("humidity")
    @Expose
    var humidity = 0
    @SerializedName("temp_kf")
    @Expose
    var temp_kf = 0.0
}