package com.example.storyapp.di

import android.content.Context
import com.example.storyapp.data.StoriesRepository
import com.example.storyapp.data.local.dao.StoriesRoomDatabase
import com.example.storyapp.data.remote.api.ApiConfig
import com.example.storyapp.ui.preference.UserPreference

object Injection {
    fun provideRepository(context: Context, userPreference: UserPreference): StoriesRepository {
        val database = StoriesRoomDatabase.getDatabase(context)
        val apiService = ApiConfig.getApiServices(userPreference)
        return StoriesRepository(database, apiService, userPreference)
    }
}