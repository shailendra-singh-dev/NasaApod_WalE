package com.nasa.nasa_apod_wale.data_integration.component

import com.nasa.nasa_apod_wale.application.ApodApp
import com.nasa.nasa_apod_wale.data_integration.module.NasaApodDbModule
import com.nasa.nasa_apod_wale.data_integration.module.ContextModule
import com.nasa.nasa_apod_wale.data_integration.module.RetrofitModule
import com.nasa.nasa_apod_wale.data_integration.module.ViewModelModule
import com.nasa.nasa_apod_wale.user_interface.NasaApodFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RetrofitModule::class, NasaApodDbModule::class, ContextModule::class, ViewModelModule::class]
)
interface AppComponent {
    fun inject(apodApp: ApodApp)

    fun inject(nasaApodFragment: NasaApodFragment)

}