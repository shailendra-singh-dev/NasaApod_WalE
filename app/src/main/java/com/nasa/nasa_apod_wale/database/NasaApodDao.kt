package com.nasa.nasa_apod_wale.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NasaApodDao {

    @Query("SELECT * FROM nasaapod ORDER BY date DESC")
    fun getAllPictures(): LiveData<List<NasaApodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(nasaApodEntity: NasaApodEntity)
}