package com.example.storyapp.data.remote.api

import com.example.storyapp.data.remote.LoginBody
import com.example.storyapp.data.remote.RegisterBody
import com.example.storyapp.data.remote.response.AddStoryResponse
import com.example.storyapp.data.remote.response.AuthResponse
import com.example.storyapp.data.remote.response.DetailStoriesResponse
import com.example.storyapp.data.remote.response.StoryResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {
    @POST("register")
    fun registerUser(
        @Body registerBody: RegisterBody
    ): Call<AuthResponse>

    @POST("login")
    fun loginUser(
        @Body loginBody: LoginBody
    ): Call<AuthResponse>

    @GET("stories")
    suspend fun getAllStories(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): StoryResponse

    @GET("stories/{id}")
    fun getDetailStories(
        @Path("id") id: String
    ): Call<DetailStoriesResponse>

    @Multipart
    @POST("stories")
    fun uploadImage(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
        @Part("lat") lat: Float?,
        @Part("lon") lon: Float?
    ): Call<AddStoryResponse>
}