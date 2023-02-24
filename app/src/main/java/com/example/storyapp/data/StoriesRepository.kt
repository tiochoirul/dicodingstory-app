package com.example.storyapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.*
import com.example.storyapp.data.local.dao.StoriesRoomDatabase
import com.example.storyapp.data.remote.api.ApiServices
import com.example.storyapp.data.remote.response.ListStoryItem
import com.example.storyapp.ui.preference.UserPreference

class StoriesRepository(
    private val database: StoriesRoomDatabase,
    private val apiServices: ApiServices,
    val userPreference: UserPreference
) {

    fun getStories(): LiveData<PagingData<ListStoryItem>> {
        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            remoteMediator = StoriesRemoteMediator(database, apiServices),
            pagingSourceFactory = {
                database.storiesDao().getStories()
            }
        ).liveData
    }

    fun getStoriesLocation(): LiveData<List<ListStoryItem>> {
        return database.storiesDao().getStoriesLocation().asLiveData()
    }
}