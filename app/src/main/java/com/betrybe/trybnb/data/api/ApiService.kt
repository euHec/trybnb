package com.betrybe.trybnb.data.api

import com.betrybe.trybnb.data.models.AuthenticationRequest
import com.betrybe.trybnb.data.models.Booking
import com.betrybe.trybnb.data.models.BookingID
import com.betrybe.trybnb.data.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("auth")
    suspend fun authenticate(@Body body: AuthenticationRequest): Response<LoginResponse>
    @GET("booking")
    suspend fun getBookings(): Response<List<BookingID>>

    @GET("booking/{id}")
    suspend fun getBookingID(
        @Path("id") id: String,
        @Header("Accept") accept: String = "*/*"
    ): Response<Booking>
}
