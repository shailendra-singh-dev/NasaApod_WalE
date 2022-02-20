package com.nasa.nasa_apod_wale.user_interface

import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NasaApodViewModel @Inject constructor(private val nasaApodRepository: NasaApodRepository) :
    ViewModel() {

    val nasaApod by lazy { nasaApodRepository.observeApod() }
    }