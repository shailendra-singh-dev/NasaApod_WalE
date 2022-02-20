package com.nasa.nasa_apod_wale.data_integration.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nasa.nasa_apod_wale.data_integration.ViewModelKey
import com.nasa.nasa_apod_wale.user_interface.NasaApodViewModel
import com.nasa.nasa_apod_wale.user_interface.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NasaApodViewModel::class)
    abstract fun nasaApodListViewModel(viewModelNasa: NasaApodViewModel): ViewModel
}