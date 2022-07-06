package com.anshad.whetherapp.data.localdata.pref

interface PreferenceProvider {
    fun getCityName():String?
    fun setCityName(city:String?)
    fun getLastUpdatedTime():String?
    fun setLastUpdatedTime(time:String?)
}