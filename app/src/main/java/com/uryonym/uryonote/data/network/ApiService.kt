package com.uryonym.uryonote.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

private const val BASE_URL = "https://api-portal.uryonym.com/api/v1/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
    .baseUrl(BASE_URL).build()

interface ApiService {
    @GET("notes")
    suspend fun getNotes(): List<NetworkNote>

    @GET("notes/{id}")
    suspend fun getNote(@Path("id") id: String): NetworkNote

    @POST("notes")
    suspend fun addNote(@Body note: NetworkNote)

    @PATCH("notes/{id}")
    suspend fun editNote(
        @Path("id") id: String,
        @Body note: NetworkNote
    ): NetworkNote

    @DELETE("notes/{id}")
    suspend fun deleteNote(@Path("id") id: String)
}

object NoteApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}