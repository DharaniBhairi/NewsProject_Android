package com.example.newsproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.lifecycleScope
import com.example.newsproject.databinding.ActivityTopicSelectionBinding
import com.example.newsproject.topicSelection.TopicFragment
import com.example.newsproject.utils.Constants
import com.example.newsproject.utils.DataPreferences
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TopicSelectionActivity : AppCompatActivity(){

    private val topicFragment = TopicFragment()
    private lateinit var binding: ActivityTopicSelectionBinding
    lateinit var userData: DataPreferences
    private lateinit var datastore: DataStore<Preferences>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTopicSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userData = DataPreferences(this)
        datastore = createDataStore("AppDataStore")

        val bundle = Bundle()
        bundle.putBoolean("DATA",true)
        topicFragment.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.fragment_topic_view,topicFragment)
            .commit()

        lifecycleScope.launch{
            userData.storeState(true,Constants.STATE_KEY)
        }
    }
}