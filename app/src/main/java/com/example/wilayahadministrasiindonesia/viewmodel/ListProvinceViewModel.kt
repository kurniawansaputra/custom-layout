package com.example.wilayahadministrasiindonesia.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wilayahadministrasiindonesia.model.ProvinceResponse
import com.example.wilayahadministrasiindonesia.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListProvinceViewModel : ViewModel() {
    private val _province = MutableLiveData<ProvinceResponse>()
    val province: LiveData<ProvinceResponse> = _province

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getProvince() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getProvince()
        client.enqueue(object : Callback<ProvinceResponse> {
            override fun onResponse(call: Call<ProvinceResponse>, response: Response<ProvinceResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _province.value = response.body() as ProvinceResponse
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ProvinceResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object {
        private const val TAG = "ListProvince"
    }
}