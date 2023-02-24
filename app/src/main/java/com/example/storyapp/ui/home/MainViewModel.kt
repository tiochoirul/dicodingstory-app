package com.example.storyapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.storyapp.data.StoriesRepository
import com.example.storyapp.data.remote.response.ListStoryItem
import kotlinx.coroutines.launch

class MainViewModel(repository: StoriesRepository) : ViewModel() {

    private val userPreference = repository.userPreference

    val stories: LiveData<PagingData<ListStoryItem>> =
        repository.getStories().cachedIn(viewModelScope)

    fun logout() {
        viewModelScope.launch {
            userPreference.clearSession()
        }
    }
}