package com.anshad.whetherapp.data.localdata.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anshad.whetherapp.data.localdata.db.tables.WeatherDataTable

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather: WeatherDataTable): Long

    @Query("Select * from weather_table")
    suspend fun getAll(): Array<WeatherDataTable>

    @Query("Delete from weather_table")
    suspend fun deleteAll()

}