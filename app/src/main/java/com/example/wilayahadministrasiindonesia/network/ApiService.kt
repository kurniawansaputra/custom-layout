package com.example.wilayahadministrasiindonesia.network

import com.example.wilayahadministrasiindonesia.model.ProvinceResponse
import com.example.wilayahadministrasiindonesia.model.RegencyResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("provinsi")
    fun getProvince(
    ): Call<ProvinceResponse>

    @GET("kota")
    fun getRegency(
        @Query("id_provinsi") idProvince: String
    ): Call<RegencyResponse>
}