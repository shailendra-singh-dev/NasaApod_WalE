package com.nasa.nasa_apod_wale

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nasa.nasa_apod_wale.databinding.ActivityMainBinding
import com.nasa.nasa_apod_wale.user_interface.NasaApodFragment

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "nasa_apod_wale"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
        if (savedInstanceState == null) {
            val fm = supportFragmentManager
            val ft = fm.beginTransaction()
            ft.add(R.id.container, NasaApodFragment.newInstance())
            ft.commit()
        }
    }

}