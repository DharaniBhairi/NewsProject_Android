package com.example.newsproject.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.newsproject.TopicSelectionActivity
import com.example.newsproject.databinding.ActivitySplashBinding
import com.example.newsproject.main.MainActivity
import com.example.newsproject.utils.Constants
import com.example.newsproject.utils.DataPreferences
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    lateinit var userData: DataPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userData = DataPreferences(this)
        lifecycleScope.launch {
            val selectedCategoriesData = userData.readCategories(Constants.CATEGORIES_KEY)
            val enteredName = userData.readUser(Constants.USER_KEY)
            if (selectedCategoriesData != null) {
                if (selectedCategoriesData.isNotEmpty()) {
                    if (enteredName != null) {
                        if (enteredName.isNotEmpty()) {
                            val intent = Intent()
                            intent.setClass(this@SplashActivity, MainActivity::class.java)
                            startActivity(intent)
                            this@SplashActivity.finish()
                        }
                    }
                    else
                    {
                        val intent = Intent()
                        intent.setClass(this@SplashActivity, TopicSelectionActivity::class.java)
                        startActivity(intent)
                        this@SplashActivity.finish()
                    }
                }
            }
            else{
                val intent = Intent()
                intent.setClass(this@SplashActivity, TopicSelectionActivity::class.java)
                startActivity(intent)
                this@SplashActivity.finish()
            }
        }
    }
}