package com.example.storyapp

import com.example.storyapp.data.remote.response.ListStoryItem
import java.util.*

class DataDummy {
    fun generateDummyStoryResponse(): List<ListStoryItem> {
        val items: MutableList<ListStoryItem> = arrayListOf()
        for (i in 0..100) {
            val story = ListStoryItem(
                "https://story-api.dicoding.dev/images/stories/photos-1676434542181_PlDxR0nv.jpg",
                Date().toString(),
                "Story $i",
                "story $i",
                null,
                i.toString(),
                null
            )
            items.add(story)
        }
        return items
    }
}