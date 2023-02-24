package com.example.storyapp.data.remote.api

import com.example.storyapp.BuildConfig
import com.example.storyapp.data.remote.AuthInterceptor
import com.example.storyapp.ui.preference.UserPreference
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {

    private val loggingInterceptor = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    } else {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    fun getApiServices(userPreference: UserPreference): ApiServices {
        val clientWithAuthInterceptor = client.newBuilder()
            .addInterceptor(AuthInterceptor(userPreference))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientWithAuthInterceptor)
            .build()

        return retrofit.create(ApiServices::class.java)
    }

    fun getAuthApiServices(): ApiServices {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiServices::class.java)
    }
}