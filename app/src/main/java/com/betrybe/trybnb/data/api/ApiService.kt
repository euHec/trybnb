package com.betrybe.trybnb.data.api

import com.betrybe.trybnb.data.models.AuthenticationRequest
import com.betrybe.trybnb.data.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("auth")
    suspend fun authenticate(@Body body: AuthenticationRequest): Response<LoginResponse>
}
