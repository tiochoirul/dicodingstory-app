package com.example.storyapp.ui.stories

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.storyapp.R
import com.example.storyapp.databinding.ActivityDetailStoriesBinding
import com.example.storyapp.tools.ViewModelFactory
import com.example.storyapp.tools.withDateFormat
import com.example.storyapp.ui.preference.UserPreference
import com.google.android.material.snackbar.Snackbar

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class DetailStoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailStoriesBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailStoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()

        val id = intent.getStringExtra(EXTRA_ID)
        getStories(id.toString())

        setupView()

        detailViewModel.isLoading.observe(this) {
            it.getContentIfNotHandled()?.let { isLoading ->
                showLoading(isLoading)
            }
        }

        showSnackBar()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupView() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra(EXTRA_NAME)
    }

    private fun setupViewModel() {
        ViewModelProvider(
            this,
            ViewModelFactory(this, UserPreference.getInstance(dataStore))
        )[DetailViewModel::class.java]
    }

    private fun getStories(id: String) {
        detailViewModel.getDetailStories(id)
        detailViewModel.stories.observe(this) { stories ->
            binding.apply {
                Glide.with(applicationContext)
                    .load(stories.photoUrl)
                    .into(ivDetailPhoto)

                val uploaded = StringBuilder(getString(R.string.upload_date)).append(" ")
                    .append(stories.createdAt.withDateFormat())

                tvDetailName.text = stories.name
                tvDetailDescription.text = stories.description
                tvDetailUploaded.text = uploaded
            }
        }
    }

    private fun showSnackBar() {
        detailViewModel.snackbar.observe(this) {
            it.getContentIfNotHandled()?.let { snackBar ->
                Snackbar.make(
                    window.decorView.rootView,
                    snackBar,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAME = "extra_name"
    }
}