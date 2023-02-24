package com.example.storyapp.data

import com.example.storyapp.data.remote.LoginBody
import com.example.storyapp.data.remote.RegisterBody
import com.example.storyapp.data.remote.api.ApiServices
import com.example.storyapp.data.remote.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.util.*

class FakeApiServices: ApiServices {
    override fun registerUser(registerBody: RegisterBody): Call<AuthResponse> {
        TODO("Not yet implemented")
    }

    override fun loginUser(loginBody: LoginBody): Call<AuthResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllStories(page: Int, size: Int): StoryResponse {
        val items: MutableList<ListStoryItem> = arrayListOf()
        for (i in 0..100) {
            val quote = ListStoryItem(
                "https://story-api.dicoding.dev/images/stories/photos-1676434542181_PlDxR0nv.jpg",
                Date().toString(),
                "Story $i",
                "story $i",
                null,
                i.toString(),
                null
            )
            items.add(quote)
        }
        return StoryResponse(items.subList((page - 1) * size, (page - 1) * size + size), false, "")
    }

    override fun getDetailStories(id: String): Call<DetailStoriesResponse> {
        TODO("Not yet implemented")
    }

    override fun uploadImage(
        file: MultipartBody.Part,
        description: RequestBody,
        lat: Float?,
        lon: Float?
    ): Call<AddStoryResponse> {
        TODO("Not yet implemented")
    }
}