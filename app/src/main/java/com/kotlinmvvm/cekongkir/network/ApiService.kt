package com.kotlinmvvm.cekongkir.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://pro.rajaongkir.com/api/"
private const val apiKey = "4bdfc655c2f1acd34f5e0c2fa4e5cbec"

object ApiService {

    fun getClient(): RajaOngkirEndPoint {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                            .addHeader("key", apiKey).build()
                    chain.proceed(request)
                }
                .build()

        val gson = GsonBuilder().serializeNulls().create()

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
                .create(RajaOngkirEndPoint::class.java)
    }

}