package com.nasa.nasa_apod_wale.user_interface

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.nasa.nasa_apod_wale.MainActivity.Companion.TAG
import com.nasa.nasa_apod_wale.application.ApodApp
import com.nasa.nasa_apod_wale.R
import com.nasa.nasa_apod_wale.binding.bindImageFromUrl
import com.nasa.nasa_apod_wale.databinding.FragmentApodBinding
import com.nasa.nasa_apod_wale.database.NasaApodEntity
import com.nasa.nasa_apod_wale.utils.Utils
import java.util.*
import com.nasa.nasa_apod_wale.data.Result
import javax.inject.Inject

class NasaApodFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        fun newInstance() = NasaApodFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ApodApp.instance.getApodAppComponent().inject(this)
        val nasaapodViewModel: NasaApodViewModel by viewModels { viewModelFactory }

        val binding = DataBindingUtil.inflate<FragmentApodBinding>(
            inflater, R.layout.fragment_apod, container, false
        )
        subscribeUi(binding,nasaapodViewModel)
        return binding.root
    }

    private fun subscribeUi(binding: FragmentApodBinding,nasaapodViewModel: NasaApodViewModel) {
        nasaapodViewModel.nasaApod.observe(viewLifecycleOwner, Observer { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.hide()
                    result.data?.let { bindView(binding, it) }
                }
                Result.Status.LOADING -> binding.progressBar.show()
                Result.Status.ERROR -> {
                    binding.progressBar.hide()
                    Snackbar.make(binding.titleTv, result.message!!, Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }
    private fun bindView(binding: FragmentApodBinding,nasaApodEntityList: List<NasaApodEntity>) {
        if (!nasaApodEntityList.isEmpty()) {
            Log.e(TAG , "List is$nasaApodEntityList")
            Log.e(TAG, "Size is "+ nasaApodEntityList.size)
            val nasaApodEntity = nasaApodEntityList.get(0);
            Log.i(TAG , "Date is " + Utils.convertDateFormat(nasaApodEntity.date))
            nasaApodEntity.apply {
                bindImageFromUrl(binding.apodImageview, nasaApodEntity.url)
                binding.titleTv.text = nasaApodEntity.title
                binding.explainTv.text = nasaApodEntity.explanation
            }

            Log.i(TAG , "Current Date is " + Utils.getCurrentDate(Calendar.getInstance()))
            Log.e(TAG ,
                ("Date compare " + Utils.convertDateFormat(nasaApodEntity.date)==(Utils.getCurrentDate(Calendar.getInstance()))).toString()
            )
            if (!Utils.convertStringToDate(Utils.convertDateFormat(nasaApodEntity.date)).equals(
                    Utils.convertStringToDate(Utils.getCurrentDate(Calendar.getInstance()))) && !Utils.isNetworkAvailable(binding.titleTv.context)) {
                Toast.makeText(
                    binding.titleTv.context,
                    getString(R.string.last_cache_image),
                    Toast.LENGTH_LONG
                ).show()
            }
        } else if (!Utils.isNetworkAvailable(binding.titleTv.context)) {
            Toast.makeText(
                binding.titleTv.context,
                getString(R.string.no_internet),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}
