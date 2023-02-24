package com.example.storyapp.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storyapp.R
import com.example.storyapp.adapter.ListStoriesAdapter
import com.example.storyapp.adapter.LoadingStateAdapter
import com.example.storyapp.data.remote.response.ListStoryItem
import com.example.storyapp.databinding.ActivityMainBinding
import com.example.storyapp.tools.ViewModelFactory
import com.example.storyapp.ui.maps.MapsActivity
import com.example.storyapp.ui.preference.UserPreference
import com.example.storyapp.ui.spalsh.WelcomeActivity
import com.example.storyapp.ui.stories.AddStoryActivity
import com.example.storyapp.ui.stories.DetailStoriesActivity

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvStory.setHasFixedSize(true)
        binding.rvStory.layoutManager = LinearLayoutManager(this)

        setupViewModel()
        getAllStories()

        binding.fabAdd.setOnClickListener { view ->
            if (view.id == R.id.fab_add) {
                val intent = Intent(this@MainActivity, AddStoryActivity::class.java)
                startActivity(intent)
            }
        }

        binding.fabMaps.setOnClickListener { view ->
            if (view.id == R.id.fab_maps) {
                MapsActivity.start(this@MainActivity)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val resultUpload = intent.getIntExtra(RESULT_UPLOAD, 0)
        if (resultUpload == 1) {
            getAllStories()
            intent.putExtra(RESULT_UPLOAD, 0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> {
                getAllStories()
            }
            R.id.action_account -> {

            }
            R.id.action_sign_out -> {
                mainViewModel.logout()
                WelcomeActivity.start(this@MainActivity)
                finish()
            }
            R.id.action_setting -> {
                startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupViewModel() {
        ViewModelProvider(
            this,
            ViewModelFactory(this, UserPreference.getInstance(dataStore))
        )[MainViewModel::class.java]
    }

    private fun getAllStories() {
        val listStoriesAdapter = ListStoriesAdapter(getString(R.string.upload_date))
        binding.rvStory.adapter = listStoriesAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                listStoriesAdapter.retry()
            }
        )
        mainViewModel.stories.observe(this) { listStories ->
            listStoriesAdapter.submitData(lifecycle, listStories)

            listStoriesAdapter.onItemClick = { _, story: ListStoryItem ->
                val intent = Intent(this@MainActivity, DetailStoriesActivity::class.java)
                intent.putExtra(DetailStoriesActivity.EXTRA_ID, story.id)
                intent.putExtra(DetailStoriesActivity.EXTRA_NAME, story.name)
                startActivity(intent)
            }
        }
    }

    companion object {
        const val RESULT_UPLOAD = "result_upload"

        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }
}