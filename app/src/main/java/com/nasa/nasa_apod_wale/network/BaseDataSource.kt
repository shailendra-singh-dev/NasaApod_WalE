package com.nasa.nasa_apod_wale.network

import android.util.Log
import com.nasa.nasa_apod_wale.MainActivity.Companion.TAG
import retrofit2.Response
import com.nasa.nasa_apod_wale.data.Result


/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        Log.e(TAG,message)
        return Result.error("Network call has failed for a following reason: $message")
    }

}

