package com.nasa.nasa_apod_wale.application

import android.app.Application
import com.nasa.nasa_apod_wale.BuildConfig
import com.nasa.nasa_apod_wale.data_integration.component.AppComponent
import com.nasa.nasa_apod_wale.data_integration.component.DaggerAppComponent
import com.nasa.nasa_apod_wale.data_integration.module.ContextModule

class ApodApp : Application() {

    companion object {
        @JvmStatic
        lateinit var instance: ApodApp
            private set
    }

    private val appComponent by lazy {
        DaggerAppComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent.inject(this)
    }

    fun getApodAppComponent(): AppComponent = appComponent
}