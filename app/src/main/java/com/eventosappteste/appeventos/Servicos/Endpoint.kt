package com.eventosappteste.appeventos.Servicos

import com.eventosappteste.appeventos.Model.CheckIn
import com.eventosappteste.appeventos.Model.Evento
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Endpoint {
    @GET("events")
    fun getEvents() : Call<List<Evento>>

    @POST("checkin")
    fun postCheckIn(@Body checkIn: CheckIn) : Call<CheckIn>
}