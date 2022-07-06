package com.anshad.whetherapp.data.localdata.db

import com.anshad.whetherapp.App
import com.anshad.whetherapp.data.localdata.db.dao.WeatherDao
import com.anshad.whetherapp.data.localdata.db.tables.WeatherDataTable
import com.anshad.whetherapp.model.ListData

class DataBaseHandler private constructor() {


    private val dataBase: DataBase by lazy {
        DataBase.getInstance(App.instance)!!
    }
    private val weatherDao: WeatherDao by lazy {
        dataBase.weatherDao()
    }

    companion object {
        val instance: DataBaseHandler by lazy {
            DataBaseHandler()
        }
    }


   suspend fun addWeatherData(list: ArrayList<ListData>?): Boolean {
        list?.forEach {

            val weatherTable = WeatherDataTable.parse(it)
            weatherDao.insert(weatherTable)

        }
       return true

    }

   suspend fun getWeatherData() : List<ListData> {
       val data = weatherDao.getAll()
       val listData = ArrayList<ListData>()
       for (weather in data) {
           listData.add(weather.toListData())
       }
       return listData
    }

    suspend fun deleteAll() {
        weatherDao.deleteAll()
    }

}