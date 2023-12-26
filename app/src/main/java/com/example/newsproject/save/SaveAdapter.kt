package com.example.newsproject.save

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsproject.R
import com.example.newsproject.dataBase.DataBaseResponse
import com.example.newsproject.databinding.SaveSingleItemBinding

class SaveAdapter(var context: Context, private var savedList: ArrayList<DataBaseResponse>): RecyclerView.Adapter<SaveAdapter.ViewHolder>() {
    class ViewHolder(var binding: SaveSingleItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SaveSingleItemBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {

            tvSaveNewsTitle.text = savedList[position].title
            if (savedList[position].image.isEmpty())
                Glide.with(context).load(R.drawable.default_image).into(holder.binding.ivSaveNews)
            else
            Glide.with(context).load(savedList[position].image).into(holder.binding.ivSaveNews)
        }
    }

    override fun getItemCount(): Int {
        return savedList.size
    }
}