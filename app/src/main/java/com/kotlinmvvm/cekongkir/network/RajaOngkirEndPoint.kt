package com.kotlinmvvm.cekongkir.network

import com.kotlinmvvm.cekongkir.network.response.CityResponse
import com.kotlinmvvm.cekongkir.network.response.CostResponse
import com.kotlinmvvm.cekongkir.network.response.SubdistrictResponse
import retrofit2.Response
import retrofit2.http.*

interface RajaOngkirEndPoint {

    @GET("city")
    suspend fun city(): Response<CityResponse>

    @GET("subdistrict")
    suspend fun subdistrict(@Query("city") city: String?): Response<SubdistrictResponse>

    @FormUrlEncoded
    @POST("cost")
    suspend fun cost(
            @Field("origin") origin: String,
            @Field("originType") originType: String,
            @Field("destination") destination: String,
            @Field("destinationType") destinationType: String,
            @Field("weight") weight: String,
            @Field("courier") courier: String
    ): Response<CostResponse>
}