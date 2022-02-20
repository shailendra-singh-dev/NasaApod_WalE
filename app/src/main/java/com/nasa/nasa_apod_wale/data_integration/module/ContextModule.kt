package com.nasa.nasa_apod_wale.data_integration.module

import android.content.Context
import com.nasa.nasa_apod_wale.data_integration.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private var context: Context) {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = context
}