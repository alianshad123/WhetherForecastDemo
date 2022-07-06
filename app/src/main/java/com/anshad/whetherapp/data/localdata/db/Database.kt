package com.anshad.whetherapp.data.localdata.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anshad.whetherapp.BuildConfig
import com.anshad.whetherapp.data.localdata.db.dao.WeatherDao
import com.anshad.whetherapp.data.localdata.db.tables.WeatherDataTable
import com.anshad.whetherapp.model.ListData

@Database(
    entities = [WeatherDataTable::class],
    version = 1
)
abstract class DataBase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
    fun addWeatherData(list: ArrayList<ListData>?) {

    }

    companion object {
        private var instance: DataBase? = null

        @JvmStatic
        fun getInstance(context: Context): DataBase? {
            if (instance == null) {
                synchronized(DataBase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataBase::class.java,
                        "weather.db"
                    ).build()
                }
            }
            return instance
        }
    }

}