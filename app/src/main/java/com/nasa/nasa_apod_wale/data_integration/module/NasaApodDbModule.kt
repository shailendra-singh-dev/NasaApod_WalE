package com.nasa.nasa_apod_wale.data_integration.module

import android.content.Context
import androidx.room.Room
import com.nasa.nasa_apod_wale.database.NasaApodDao
import com.nasa.nasa_apod_wale.database.NasaApodDb
import com.nasa.nasa_apod_wale.data_integration.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NasaApodDbModule {
    @Provides
    @Singleton
    fun provideApodDb(@ApplicationContext context: Context): NasaApodDb = Room
        .databaseBuilder(context, NasaApodDb::class.java, "apodapp.db")
        .build()

    @Provides
    @Singleton
    fun provideApodDao(apodDb: NasaApodDb): NasaApodDao = apodDb.NasaApodDao()

}
