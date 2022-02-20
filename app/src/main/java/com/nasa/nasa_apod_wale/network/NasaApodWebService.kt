package com.nasa.nasa_apod_wale.network

import com.nasa.nasa_apod_wale.database.NasaApodEntity
import retrofit2.Response
import retrofit2.http.GET

interface NasaApodWebService {

    @GET("planetary/apod")
    suspend fun getPictureOfTheDay(): Response<NasaApodEntity>

}