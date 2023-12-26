package com.example.newsproject.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.newsproject.R
import com.example.newsproject.TopicSelectionActivity
import com.example.newsproject.databinding.ActivityMainBinding
import com.example.newsproject.home.HomeFragment
import com.example.newsproject.save.SaveFragment
import com.example.newsproject.topicSelection.TopicFragment
import com.example.newsproject.utils.Constants
import com.example.newsproject.utils.DataPreferences
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private var topicFragment = TopicFragment()
    lateinit var userData: DataPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userData = DataPreferences(this)
        setCurrentFragment(HomeFragment())

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_fragment -> {
                    setCurrentFragment(HomeFragment())
                    true
                }
                R.id.save_fragment -> {
                    setCurrentFragment(SaveFragment())
                    true
                }

                R.id.settings_fragment -> {
                    lifecycleScope.launch{
                        userData.storeState(false,Constants.STATE_KEY)
                    }
                    setCurrentFragment(TopicFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_view, fragment)
        transaction.commit()
    }

}