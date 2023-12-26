package com.example.newsproject.newsDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.newsproject.databinding.ActivityNewsDetailsBinding

class NewsDetailsActivity : AppCompatActivity() {

    lateinit var binding : ActivityNewsDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBackArrow.setOnClickListener{
            this@NewsDetailsActivity.finish()
        }

        val image = intent.getStringExtra("image_data")
        val description = intent.getStringExtra("description_data")

        Glide.with(this).load(image).into(binding.ivDetails)
        binding.tvDetails.text = description
    }
}