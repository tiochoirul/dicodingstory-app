package com.example.storyapp.tools

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.storyapp.di.Injection
import com.example.storyapp.ui.home.MainViewModel
import com.example.storyapp.ui.login.LoginViewModel
import com.example.storyapp.ui.maps.MapsViewModel
import com.example.storyapp.ui.preference.UserPreference
import com.example.storyapp.ui.stories.AddStoryViewModel
import com.example.storyapp.ui.stories.DetailViewModel

@Suppress("UNREACHABLE_CODE")
class ViewModelFactory(private val context: Context, private val userPreference: UserPreference) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                return LoginViewModel(userPreference) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                return MainViewModel(Injection.provideRepository(context, userPreference)) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(userPreference) as T
            }
            modelClass.isAssignableFrom(AddStoryViewModel::class.java) -> {
                return AddStoryViewModel(userPreference) as T
            }
            modelClass.isAssignableFrom(MapsViewModel::class.java) -> {
                return MapsViewModel(Injection.provideRepository(context, userPreference)) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class:" + modelClass.name)
        }
    }
}