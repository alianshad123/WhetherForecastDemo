package com.anshad.whetherapp.data.localdata.db.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anshad.whetherapp.model.ListData
import com.anshad.whetherapp.model.Main

@Entity(tableName = "weather_table")
data class WeatherDataTable (
    @PrimaryKey(autoGenerate = true) var id: Long?=null,
    @ColumnInfo(name = "temp_min") var tempMin: Double?=null,
    @ColumnInfo(name = "temp_max") var tempMax: Double?=null,
    @ColumnInfo(name = "humidity") var humidity: Int?=null,
    @ColumnInfo(name = "date") var date: Long?=null
) {

    companion object {
        @JvmStatic
        fun parse(weather: ListData): WeatherDataTable {
            return WeatherDataTable(
                tempMin = weather.main?.temp_min,
                tempMax = weather.main?.temp_max,
                humidity = weather.main?.humidity,
                date = weather.dt
            )
        }
    }

    fun toListData(): ListData {
        val listData = ListData()
        listData.dt = date ?: 0
        val main=Main()
        main?.temp_min =tempMin?:0.0
        main?.temp_max =tempMax?:0.0
        main?.humidity =humidity?:0
        listData.main=main
        return listData
    }


}