package com.anshad.whetherapp.data.localdata.pref

import android.content.Context
import com.anshad.whetherapp.App
import com.qaptive.core.ktx.PreferenceHelper
import com.qaptive.core.ktx.PreferenceHelper.get
import com.qaptive.core.ktx.PreferenceHelper.set
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultPreference @Inject constructor(@ApplicationContext context: Context) :
    PreferenceProvider {

    object Fields {
        const val CITY = "city"
        const val UPDATEDTIME = "updatedtime"
    }


    private val sharedPreferences by lazy {
        PreferenceHelper.customPrefs(context, "")
    }

    companion object {
        val instance: DefaultPreference by lazy { DefaultPreference(App.instance.applicationContext) }
    }




    override fun getCityName(): String? {
        return sharedPreferences[Fields.CITY]
    }

    override fun setCityName(city: String?) {
        sharedPreferences[Fields.CITY] = city
    }

    override fun getLastUpdatedTime(): String? {
        return sharedPreferences[Fields.UPDATEDTIME]
    }

    override fun setLastUpdatedTime(time: String?) {
        sharedPreferences[Fields.UPDATEDTIME] = time
    }
}