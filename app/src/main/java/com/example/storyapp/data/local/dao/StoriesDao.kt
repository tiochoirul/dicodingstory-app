package com.example.storyapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.storyapp.data.remote.response.ListStoryItem
import kotlinx.coroutines.flow.Flow

@Dao
interface StoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stories: List<Stories>)

    @Query("SELECT * from Stories")
    fun getStories(): PagingSource<Int, ListStoryItem>

    @Query("SELECT * from Stories WHERE lat <> 'null' and lon <> 'null'")
    fun getStoriesLocation(): Flow<List<ListStoryItem>>

    @Query("DELETE FROM Stories")
    suspend fun deleteAll()
}