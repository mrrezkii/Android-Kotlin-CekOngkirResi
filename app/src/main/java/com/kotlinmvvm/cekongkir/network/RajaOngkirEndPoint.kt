package com.kotlinmvvm.cekongkir.network

import com.kotlinmvvm.cekongkir.network.response.CityResponse
import com.kotlinmvvm.cekongkir.network.response.SubdistrictResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RajaOngkirEndPoint {

    @GET("city")
    suspend fun city(): Response<CityResponse>

    @GET("subdistrict")
    suspend fun subdistrict(@Query("city") city: String?): Response<SubdistrictResponse>
}