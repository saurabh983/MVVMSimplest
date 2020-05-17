package com.pins.exampleapp.di

import com.pins.exampleapp.models.LiveTvDetail
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

//    @FormUrlEncoded
//    @POST("login")
//    suspend fun login(
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): MainResponce

    @GET("users")
    suspend fun getUsers(): MutableList<LiveTvDetail>


}