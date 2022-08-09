package com.example.wilayahadministrasiindonesia.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wilayahadministrasiindonesia.model.ProvinceResponse
import com.example.wilayahadministrasiindonesia.model.RegencyResponse
import com.example.wilayahadministrasiindonesia.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _regency = MutableLiveData<RegencyResponse>()
    val regency: LiveData<RegencyResponse> = _regency

    fun getRegency(idProvince: String) {
        val client = ApiConfig.getApiService().getRegency(idProvince)
        client.enqueue(object : Callback<RegencyResponse> {
            override fun onResponse(call: Call<RegencyResponse>, response: Response<RegencyResponse>) {
                if (response.isSuccessful) {
                    _regency.value = response.body() as RegencyResponse
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RegencyResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object {
        private const val TAG = "Main"
    }
}