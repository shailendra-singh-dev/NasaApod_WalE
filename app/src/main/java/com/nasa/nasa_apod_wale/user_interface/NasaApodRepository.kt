package com.nasa.nasa_apod_wale.user_interface

import androidx.lifecycle.distinctUntilChanged
import com.nasa.nasa_apod_wale.data.resultLiveData
import com.nasa.nasa_apod_wale.database.NasaApodDao
import com.nasa.nasa_apod_wale.network.ApodNetworkDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NasaApodRepository @Inject constructor(
    private val nasaapodDao: NasaApodDao,
    private val apodNetworkDataSource: ApodNetworkDataSource,
) {
    fun observeApod() = resultLiveData(
        databaseQuery = { nasaapodDao.getAllPictures() },
        networkCall = { apodNetworkDataSource.fetchNasaApod() },
        saveCallResult = { nasaapodDao.insertPicture(it) })
        .distinctUntilChanged()
}

