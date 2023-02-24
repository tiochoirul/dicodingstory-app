package com.example.storyapp.ui.spalsh

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.storyapp.databinding.ActivitySplashBinding
import com.example.storyapp.tools.ViewModelFactory
import com.example.storyapp.ui.*
import com.example.storyapp.ui.home.MainActivity
import com.example.storyapp.ui.login.LoginViewModel
import com.example.storyapp.ui.preference.UserPreference

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val loginViewModel by viewModels<LoginViewModel>()
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        Handler(Looper.getMainLooper()).postDelayed({
            setupSession()
        }, 2000)
    }

    private fun setupSession() {
        ViewModelProvider(
            this,
            ViewModelFactory(this, UserPreference.getInstance(dataStore))
        )[LoginViewModel::class.java]

        loginViewModel.getUser().observe(this) { user ->
            if (!user.isLogin) {
                WelcomeActivity.start(this@SplashActivity)
                finish()
            } else {
                MainActivity.start(this@SplashActivity)
                finish()
            }
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}