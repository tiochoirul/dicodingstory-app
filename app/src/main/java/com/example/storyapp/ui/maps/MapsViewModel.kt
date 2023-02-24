package com.example.storyapp.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.storyapp.data.StoriesRepository
import com.example.storyapp.data.remote.response.ListStoryItem

class MapsViewModel(private val repository: StoriesRepository) : ViewModel() {

    fun getStoriesLocation(): LiveData<List<ListStoryItem>> {
        return repository.getStoriesLocation()
    }
}