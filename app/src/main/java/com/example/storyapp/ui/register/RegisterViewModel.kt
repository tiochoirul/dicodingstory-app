package com.example.storyapp.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.storyapp.data.remote.RegisterBody
import com.example.storyapp.data.remote.api.ApiConfig
import com.example.storyapp.data.remote.response.AuthResponse
import com.example.storyapp.tools.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Event<Boolean>>()
    val isLoading: LiveData<Event<Boolean>> = _isLoading

    private val _result = MutableLiveData<Event<Boolean>>()
    val result: LiveData<Event<Boolean>> = _result

    private val _snackBar = MutableLiveData<Event<String>>()
    val snackBar: LiveData<Event<String>> = _snackBar

    fun registerUser(registerBody: RegisterBody) {

        _isLoading.value = Event(true)
        _result.value = Event(false)

        val service = ApiConfig.getAuthApiServices().registerUser(registerBody)
        service.enqueue(object : Callback<AuthResponse> {
            override fun onResponse(
                call: Call<AuthResponse>,
                response: Response<AuthResponse>
            ) {
                _isLoading.value = Event(false)
                if (response.isSuccessful) {
                    _result.value = Event(true)
                    _snackBar.value = Event("Success: ${response.message()}")
                } else {
                    Log.d(TAG, "Response onFailure: ${response.message()}")
                    _snackBar.value = Event("Response onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                _isLoading.value = Event(false)

                Log.d(TAG, "onFailure: ${t.message}")
                _snackBar.value = Event("onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "RegisterViewModel"
    }
}