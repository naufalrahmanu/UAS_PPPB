package com.example.uas_4.network

import com.example.uas_4.model.Product
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("product")
    fun getAllEvents(): Call<List<Product>>

    @POST("product")
    fun addEvent(@Body requestBody: RequestBody): Call<Product>


    @POST("product/{id}")
    fun updateEvent(@Path("id") eventId: String, @Body event: Product): Call<Product>


    @DELETE("product/{id}")
    fun deleteEvent(@Path("id") id: String): Call<Unit>
}