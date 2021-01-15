package com.eventosappteste.appeventos.Servicos

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {
    companion object {
        fun getRetrofitInstance() : Endpoint {

            val path = "https://5f5a8f24d44d640016169133.mockapi.io/api/"

            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Endpoint::class.java)
        }
    }
}